package org.sebastiansiarczynski.interfaces;

import java.time.ZonedDateTime;
import java.util.List;
import org.sebastiansiarczynski.dto.EndedGameDto;
import org.sebastiansiarczynski.dto.StartGameDto;
import org.sebastiansiarczynski.dto.UpdateGameDto;
import org.sebastiansiarczynski.exception.GamesStoreException;

public interface Board {

  String startGame(StartGameDto startGameDto) throws GamesStoreException;

  boolean finishGame(String gameUuid, ZonedDateTime endDate) throws GamesStoreException;

  boolean updateGameScore(UpdateGameDto updateGameDto) throws GamesStoreException;

  List<EndedGameDto> getEndedGames() throws GamesStoreException;
}
