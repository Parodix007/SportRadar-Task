package org.sebastiansiarczynski;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sebastiansiarczynski.exception.GamesStoreException;

@RequiredArgsConstructor
public final class GamesStore {

  private final Map<String, StartedGame> startedGames;
  private final Map<String, EndedGame> endedGames;

  public static GamesStore newEmptyInstance() {
    return new GamesStore(new HashMap<>(), new HashMap<>());
  }

  public static GamesStore withStartedGames(@NonNull final Map<String, StartedGame> startedGames) {
    return new GamesStore(startedGames, new HashMap<>());
  }


  StartedGame getStartedByGameUuid(final String gameUuid) throws GamesStoreException {
    if (!startedGames.containsKey(gameUuid)) {
      throw new GamesStoreException("There is no started game with this uuid:" + gameUuid);
    }

    return startedGames.get(gameUuid);
  }

  void startGame(final String gameUuid, final StartedGame game) throws GamesStoreException {
    if (startedGames.containsKey(gameUuid) || startedGames.containsValue(game)) {
      throw new GamesStoreException(
          "This game is already started! uuid: " + gameUuid + " Game: " + game);
    }

    startedGames.put(gameUuid, game);
  }

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

  boolean updateGame(final String gameUuid, final StartedGame game) throws GamesStoreException {
    if (startedGames.replace(gameUuid, game) == null) {
      throw new GamesStoreException("There is no started game to update! uuid:" + gameUuid);
    }

    return true;
  }

  List<EndedGame> getFinishGames() {
    return endedGames.values().stream().toList();
  }
}
