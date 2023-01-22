package org.sebastiansiarczynski;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.sebastiansiarczynski.dto.EndedGameDto;
import org.sebastiansiarczynski.dto.StartGameDto;
import org.sebastiansiarczynski.dto.UpdateGameDto;
import org.sebastiansiarczynski.exception.GamesStoreException;

class GamesBoardTest {

  @Mock
  private GamesStore gamesStore;
  private GamesBoard gamesBoard;
  private StartGameDto startGameDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    gamesBoard = new GamesBoard(gamesStore);
    startGameDto = new StartGameDto("some-home-team", "some-away-team",
        ZonedDateTime.now());
  }

  @AfterEach
  void tearDown() {
    Mockito.clearAllCaches();
  }

  @Test
  @DisplayName("Should properly start a game")
  void startGame() throws GamesStoreException {
    //when
    String gameUUID = gamesBoard.startGame(startGameDto);

    Mockito.doNothing().when(gamesStore)
        .startGame(Mockito.anyString(), Mockito.any(StartedGame.class));

    //then
    assertFalse(gameUUID.isEmpty());
  }

  @Test
  @DisplayName("Should properly finish a game")
  void finishGame() throws GamesStoreException {
    //given
    String gameUuid;
    StartedGame mockStartedGame = new StartedGame("", "", 1, 1, ZonedDateTime.now());

    Mockito.when(gamesStore.getStartedByGameUuid(Mockito.anyString())).thenReturn(mockStartedGame);
    Mockito.when(gamesStore.finishGame(Mockito.anyString(), Mockito.any(EndedGame.class)))
        .thenReturn(true);

    //when
    gameUuid = gamesBoard.startGame(startGameDto);
    boolean isFinish = gamesBoard.finishGame(gameUuid, ZonedDateTime.now());

    //then
    assertTrue(isFinish);

    Mockito.verify(gamesStore, Mockito.times(1)).getStartedByGameUuid(Mockito.anyString());
    Mockito.verify(gamesStore, Mockito.times(1))
        .finishGame(Mockito.anyString(), Mockito.any(EndedGame.class));

  }

  @Test
  void updateGameScore() throws GamesStoreException {
    //given
    String gameUuid;
    UpdateGameDto updateGameDto;
    StartedGame mockStartedGame = new StartedGame("", "", 1, 1, ZonedDateTime.now());

    Mockito.when(gamesStore.getStartedByGameUuid(Mockito.anyString())).thenReturn(mockStartedGame);
    Mockito.when(gamesStore.updateGame(Mockito.anyString(), Mockito.any(StartedGame.class)))
        .thenReturn(true);

    //when
    gameUuid = gamesBoard.startGame(startGameDto);
    updateGameDto = new UpdateGameDto(gameUuid, 1, 1);
    boolean isUpdated = gamesBoard.updateGameScore(updateGameDto);

    //then
    assertTrue(isUpdated);

    Mockito.verify(gamesStore, Mockito.times(1)).getStartedByGameUuid(Mockito.anyString());
    Mockito.verify(gamesStore, Mockito.times(1))
        .updateGame(Mockito.anyString(), Mockito.any(StartedGame.class));
  }

  @Test
  @DisplayName("Should return properly sorted list of ended games")
  void getEndedGames() {
    //given
    ZonedDateTime now = ZonedDateTime.now();
    String name = "some-name";
    EndedGame firstEndedGame = new EndedGame(name, name, 1, 10,
        now);
    EndedGame secondEndedGame = new EndedGame(name, name, 1, 2,
        now);
    EndedGame thirdEndedGame = new EndedGame(name, name, 1, 1, now);

    List<EndedGame> mockGames = List.of(firstEndedGame, secondEndedGame, thirdEndedGame);

    Mockito.when(gamesStore.getFinishGames()).thenReturn(mockGames);

    //when
    List<EndedGameDto> endedGames = gamesBoard.getEndedGames();
    EndedGameDto firstSortedGame = endedGames.get(0);
    EndedGameDto secondSortedGame = endedGames.get(1);
    EndedGameDto thirdSortedGame = endedGames.get(2);

    //then
    assertEquals(3, endedGames.size());

    assertEquals(firstSortedGame.homeScore(), thirdEndedGame.getHomeScore());
    assertEquals(firstSortedGame.awayScore(), thirdEndedGame.getAwayScore());

    assertEquals(secondSortedGame.homeScore(), firstEndedGame.getHomeScore());
    assertEquals(secondSortedGame.awayScore(), firstEndedGame.getAwayScore());

    assertEquals(thirdSortedGame.homeScore(), secondEndedGame.getHomeScore());
    assertEquals(thirdSortedGame.awayScore(), secondEndedGame.getAwayScore());

    Mockito.verify(gamesStore, Mockito.times(1)).getFinishGames();
  }

  @Test
  @DisplayName("Should return list of ended games no sorted")
  void getEndedGamesNoSorted() {
    //given
    ZonedDateTime now = ZonedDateTime.now();
    String name = "some-name";
    EndedGame firstEndedGame = new EndedGame(name, name, 1, 10,
        now);
    EndedGame secondEndedGame = new EndedGame(name, name, 1, 2,
        now);

    List<EndedGame> mockGames = List.of(firstEndedGame, secondEndedGame);

    List<EndedGameDto> properResult = mockGames.stream()
        .map(endedGame -> new EndedGameDto(endedGame.homeTeam, endedGame.awayTeam,
            endedGame.getHomeScore(), endedGame.getAwayScore(), endedGame.getEndDate())).toList();

    Mockito.when(gamesStore.getFinishGames()).thenReturn(mockGames);

    //when
    List<EndedGameDto> endedGames = gamesBoard.getEndedGames();

    //then
    assertTrue(endedGames.containsAll(properResult));
  }
}