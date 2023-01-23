package org.sebastiansiarczynski;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sebastiansiarczynski.dto.EndedGameDto;
import org.sebastiansiarczynski.dto.StartGameDto;
import org.sebastiansiarczynski.dto.UpdateGameDto;
import org.sebastiansiarczynski.exception.GamesStoreException;
import org.sebastiansiarczynski.interfaces.Board;

@RequiredArgsConstructor
public final class GamesBoard implements Board {

  private final GamesStore gamesStore;


  public String startGame(@NonNull final StartGameDto startGame) throws GamesStoreException {

    final StartedGame startedGame = new StartedGame(startGame.homeTeam(), startGame.awayTeam(), 0,
        0,
        startGame.startDate());

    final String gameUUID = UUID.randomUUID().toString();

    gamesStore.startGame(gameUUID, startedGame);

    return gameUUID;
  }

  public boolean finishGame(@NonNull final String gameUUID, @NonNull final ZonedDateTime endDate)
      throws GamesStoreException {

    final StartedGame gameToFinish = gamesStore.getStartedByGameUuid(gameUUID);

    final EndedGame endedGame = new EndedGame(gameToFinish.homeTeam, gameToFinish.awayTeam,
        gameToFinish.getHomeScore(), gameToFinish.getAwayScore(), endDate);

    return gamesStore.finishGame(gameUUID, endedGame);
  }

  public boolean updateGameScore(@NonNull final UpdateGameDto updateGame)
      throws GamesStoreException {

    final StartedGame gameToUpdate = gamesStore.getStartedByGameUuid(updateGame.gameUUID());
    gameToUpdate.setHomeScore(updateGame.homeScore());
    gameToUpdate.setAwayScore(updateGame.awayScore());

    return gamesStore.updateGame(updateGame.gameUUID(), gameToUpdate);
  }

  public List<EndedGameDto> getEndedGames() {
    final List<EndedGameDto> endedGameDtos = new ArrayList<>(
        gamesStore.getFinishGames().stream()
            .map(endedGame -> new EndedGameDto(endedGame.homeTeam,
                endedGame.awayTeam, endedGame.getHomeScore(), endedGame.getAwayScore(),
                endedGame.getEndDate())).toList());

    final List<EndedGameDto> gamesWithTheSameScore =
        endedGameDtos.stream()
            .filter(endedGameDto -> endedGameDto.homeScore() == endedGameDto.awayScore())
            .toList();

    if (gamesWithTheSameScore.isEmpty()) {
      return endedGameDtos;
    }

    endedGameDtos.removeAll(gamesWithTheSameScore);
    endedGameDtos.addAll(0, gamesWithTheSameScore);

    return endedGameDtos;
  }
}
