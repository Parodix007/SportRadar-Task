package org.sebastiansiarczynski;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sebastiansiarczynski.exception.GamesStoreException;

final class GamesStoreTest {

  private GamesStore gamesStore;
  private String gameUuid;
  private StartedGame startedGame;
  private EndedGame endedGame;

  @BeforeEach
  void setUp() {
    gamesStore = new GamesStore(new HashMap<>(), new HashMap<>());
    gameUuid = UUID.randomUUID().toString();
    startedGame = new StartedGame("some-home-team", "some-away-team", 0, 0,
        ZonedDateTime.now());
    endedGame = new EndedGame("some-home-team", "some-away-team", 0, 0, ZonedDateTime.now(), ZonedDateTime.now());
  }

  @Test
  @DisplayName("Should return proper Game")
  void getStartedByGameUuid() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, startedGame);
    final Game startedByGameUuid = gamesStore.getStartedByGameUuid(gameUuid);

    //then
    assertEquals(startedGame, startedByGameUuid);
  }

  @Test
  @DisplayName("Should throw when no game with passed gameUuid")
  void shouldThrowsWhenNoGameWithPassedUuid() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, startedGame);

    //then
    assertThrows(GamesStoreException.class,
        () -> gamesStore.getStartedByGameUuid("some-random-uuid"));
  }

  @Test
  @DisplayName("Should properly start a new game")
  void startGame() {
    //when & then
    assertDoesNotThrow(() -> gamesStore.startGame(gameUuid, startedGame));
  }

  @Test
  @DisplayName("Should throw when already is stored game with the same uuid")
  void shouldThrowWhenAlreadyStoredGameWithTheSameUuid() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, startedGame);

    //then
    assertThrows(GamesStoreException.class, () -> gamesStore.startGame(gameUuid, startedGame));
  }

  @Test
  @DisplayName("Should throw when try to start the same game")
  void shouldThrowsWhenTryToStartTheSameGame() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, startedGame);

    //then
    assertThrows(GamesStoreException.class,
        () -> gamesStore.startGame(UUID.randomUUID().toString(), startedGame));
  }

  @Test
  @DisplayName("Should properly finish game")
  void finishGame() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, startedGame);

    //then
    assertTrue(gamesStore.finishGame(gameUuid, endedGame));
  }

  @Test
  @DisplayName("Should throw when already finish game with passed UUID")
  void shouldThrowWhenAlreadyFinishGame() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, startedGame);
    gamesStore.finishGame(gameUuid, endedGame);

    //then
    assertThrows(GamesStoreException.class, () -> gamesStore.finishGame(gameUuid, endedGame));
  }

  @Test
  @DisplayName("Should throw when try to finish the same game")
  void shouldThrowWhenTryToFinishTheSameGame() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, startedGame);
    gamesStore.finishGame(gameUuid, endedGame);

    //then
    assertThrows(GamesStoreException.class,
        () -> gamesStore.finishGame(UUID.randomUUID().toString(),
            endedGame));
  }

  @Test
  @DisplayName("Should throw when try to finish game which is not started")
  void shouldThrowWhenTryToFinishNotStartedGame() {
    //when & then
    assertThrows(GamesStoreException.class, () -> gamesStore.finishGame(gameUuid, endedGame));
  }

  @Test
  @DisplayName("Should properly update a game")
  void updateGame() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, startedGame);
    startedGame.setAwayScore(1);

    //then
    assertDoesNotThrow(() -> gamesStore.updateGame(gameUuid, startedGame));
  }

  @Test
  @DisplayName("Should throw when no started game to update")
  void shouldThrowWhenNoGameToUpdate() {
    //when & then
    assertThrows(GamesStoreException.class, () -> gamesStore.updateGame(gameUuid, startedGame));
  }
}