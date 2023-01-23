package org.sebastiansiarczynski;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sebastiansiarczynski.exception.GamesStoreException;

/**
 * Implementation of a simple in-memory database containing started and ended games, required to be
 * injected to the {@link GamesBoard}
 *
 * @author Sebastian Siarczyński
 */
@RequiredArgsConstructor
public final class GamesStore {

  private final Map<String, StartedGame> startedGames;
  private final Map<String, EndedGame> endedGames;

  /**
   * Factory method which returns {@link GamesStore} instance with empty database
   *
   * @return New instance of the {@link GamesStore}
   */
  public static GamesStore newEmptyInstance() {
    return new GamesStore(new HashMap<>(), new HashMap<>());
  }

  /**
   * Factory method which allows to create a new instance of the {@link GamesStore} with started
   * games <b>with initial score 0-0 for each game</b>
   *
   * @param startedGames {@link Map} With started games
   * @return New instance of the {@link GamesStore}
   */
  public static GamesStore withStartedGames(@NonNull final Map<String, StartedGame> startedGames) {
    return new GamesStore(startedGames, new HashMap<>());
  }

  /**
   * Method which allows to get a started game by {@code gameUuid}
   *
   * @param gameUuid {@link String} Uuid of the seeking game
   * @return {@link StartedGame} Instance of the started game
   * @throws GamesStoreException When no game with passed uuid
   */
  StartedGame getStartedByGameUuid(final String gameUuid) throws GamesStoreException {
    if (!startedGames.containsKey(gameUuid)) {
      throw new GamesStoreException("There is no started game with this uuid:" + gameUuid);
    }

    return startedGames.get(gameUuid);
  }

  /**
   * Method which allows to start a new game
   *
   * @param gameUuid {@link String} New game uniq uuid
   * @param game     {@link StartedGame} Instance of new started game
   * @throws GamesStoreException When there is already such game started
   */
  void startGame(final String gameUuid, final StartedGame game) throws GamesStoreException {
    if (startedGames.containsKey(gameUuid) || startedGames.containsValue(game)) {
      throw new GamesStoreException(
          "This game is already started! uuid: " + gameUuid + " Game: " + game);
    }

    startedGames.put(gameUuid, game);
  }

  /**
   * Method which allows to finish a game
   *
   * @param gameUuid {@link String} Uniq uuid of the game to finish
   * @param game     {@link EndedGame} Instance of ended game
   * @return If successfully finish game
   * @throws GamesStoreException When can't finish game or if is such game finished
   */
  boolean finishGame(final String gameUuid, final EndedGame game) throws GamesStoreException {
    if (endedGames.containsKey(gameUuid) || endedGames.containsValue(game)) {
      throw new GamesStoreException(
          "This game is already finished! uuid" + gameUuid + " Game: " + game);
    }
    if (startedGames.remove(gameUuid) == null) {
      throw new GamesStoreException("There is no started game with this uuid: " + gameUuid);
    }

    endedGames.put(gameUuid, game);

    return true;
  }

  /**
   * Method which allows to update a started game
   *
   * @param gameUuid {@link String} Uniq UUID of the game
   * @param game     {@link StartedGame} Updated previously started game
   * @return If successfully update the game
   * @throws GamesStoreException When can't update the game
   */
  boolean updateGame(final String gameUuid, final StartedGame game) throws GamesStoreException {
    if (startedGames.replace(gameUuid, game) == null) {
      throw new GamesStoreException("There is no started game to update! uuid:" + gameUuid);
    }

    return true;
  }

  /**
   * Method which allows to get all finished games
   *
   * @return Finished games
   */
  List<EndedGame> getFinishGames() {
    return endedGames.values().stream().toList();
  }
}
