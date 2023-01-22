package org.sebastiansiarczynski;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sebastiansiarczynski.exception.GamesStoreException;

final class GamesStoreTest {

  private GamesStore gamesStore;
  private Game game;
  private String gameUuid;

  @BeforeEach
  void setUp() {
    gamesStore = new GamesStore(new HashMap<>(), new HashMap<>());
    game = new Game();
    gameUuid = UUID.randomUUID().toString();
  }

  @Test
  @DisplayName("Should return proper Game")
  void getStartedByGameUuid() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, game);
    Game startedByGameUuid = gamesStore.getStartedByGameUuid(gameUuid);

    //then
    assertEquals(game, startedByGameUuid);
  }

  @Test
  @DisplayName("Should throw when no game with passed gameUuid")
  void shouldThrowsWhenNoGameWithPassedUuid() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, game);

    //then
    assertThrows(GamesStoreException.class,
        () -> gamesStore.getStartedByGameUuid("some-random-uuid"));
  }

  @Test
  @DisplayName("Should properly start a new game")
  void startGame() {
    //when & then
    assertDoesNotThrow(() -> gamesStore.startGame(gameUuid, game));
  }

  @Test
  @DisplayName("Should throw when already is stored game with the same uuid")
  void shouldThrowWhenAlreadyStoredGameWithTheSameUuid() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, game);

    //then
    assertThrows(GamesStoreException.class, () -> gamesStore.startGame(gameUuid, game));
  }

  @Test
  @DisplayName("Should properly finish game")
  void finishGame() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, game);

    //then
    assertTrue(gamesStore.finishGame(gameUuid, game));
  }

  @Test
  @DisplayName("Should throw when already finish game")
  void shouldThrowWhenAlreadyFinishGame() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, game);
    gamesStore.finishGame(gameUuid, game);

    //then
    assertThrows(GamesStoreException.class, () -> gamesStore.finishGame(gameUuid, game));
  }

  @Test
  @DisplayName("Should throw when try to finish game which is not started")
  void shouldThrowWhenTryToFinishNotStartedGame() {
    //when & then
    assertThrows(GamesStoreException.class, () -> gamesStore.finishGame(gameUuid, game));
  }

  @Test
  @DisplayName("Should properly update a game")
  void updateGame() throws GamesStoreException {
    //when
    gamesStore.startGame(gameUuid, game);
    game.setAwayScore(1);

    //then
    assertDoesNotThrow(() -> gamesStore.updateGame(gameUuid, game));
  }

  @Test
  @DisplayName("Should throw when no started game to update")
  void shouldThrowWhenNoGameToUpdate() {
    //when & then
    assertThrows(GamesStoreException.class, () -> gamesStore.updateGame(gameUuid, game));
  }
}