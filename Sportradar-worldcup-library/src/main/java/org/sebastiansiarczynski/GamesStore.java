package org.sebastiansiarczynski;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.sebastiansiarczynski.exception.GamesStoreException;

@RequiredArgsConstructor
final class GamesStore {

  private final Map<String, Game> startedGames;
  private final Map<String, Game> endedGames;


  Game getStartedByGameUuid(final String gameUuid) throws GamesStoreException {
    if (!startedGames.containsKey(gameUuid)) {
      throw new GamesStoreException("There is no started game with this uuid:" + gameUuid);
    }

    return startedGames.get(gameUuid);
  }

  void startGame(final String uuid, final Game game) throws GamesStoreException {
    if (startedGames.containsKey(uuid)) {
      throw new GamesStoreException(
          "This game is already started! uuid: " + uuid + " Game: " + game);
    }

    startedGames.put(uuid, game);
  }

  boolean finishGame(final String gameUuid, final Game game) throws GamesStoreException {
    if (endedGames.containsKey(gameUuid)) {
      throw new GamesStoreException("There is already finished game with this uuid: " + gameUuid);
    }
    if (startedGames.remove(gameUuid) == null) {
      throw new GamesStoreException("There is no started game with this uuid: " + gameUuid);
    }

    endedGames.put(gameUuid, game);

    return true;
  }

  boolean updateGame(final String gameUuid, final Game game) throws GamesStoreException {
    if (startedGames.replace(gameUuid, game) == null) {
      throw new GamesStoreException("There is no started game to update! uuid:" + gameUuid);
    }

    return true;
  }

  List<Game> getFinishGames() {
    return endedGames.values().stream().toList();
  }
}
