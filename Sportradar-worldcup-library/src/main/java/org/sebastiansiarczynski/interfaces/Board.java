package org.sebastiansiarczynski.interfaces;

import java.time.ZonedDateTime;
import java.util.List;
import org.sebastiansiarczynski.dto.EndedGameDto;
import org.sebastiansiarczynski.dto.StartGameDto;
import org.sebastiansiarczynski.dto.UpdateGameDto;
import org.sebastiansiarczynski.exception.GamesStoreException;

/**
 * Representation of a Board in terms of this library. Example of implementation:
 * {@link org.sebastiansiarczynski.GamesBoard}
 *
 * @author Sebastian Siarczyński
 */
public interface Board {

  /**
   * Method which allows to start a game
   *
   * @param startGameDto {@link StartGameDto}
   * @return Uniq UUID of the game
   * @throws GamesStoreException When any error occur while starting a new game
   */
  String startGame(StartGameDto startGameDto) throws GamesStoreException;

  /**
   * Method which allows to finish previously started game
   *
   * @param gameUuid Uniq UUID of the game
   * @param endDate  Date of the game finish
   * @return If successfully finished game
   * @throws GamesStoreException When any error occur while finishing a game
   */
  boolean finishGame(String gameUuid, ZonedDateTime endDate) throws GamesStoreException;

  /**
   * Method which allows to update score of previously started game
   *
   * @param updateGameDto {@link UpdateGameDto}
   * @return If successfully update the game
   * @throws GamesStoreException When any error occur while updating the game
   */
  boolean updateGameScore(UpdateGameDto updateGameDto) throws GamesStoreException;

  /**
   * Method which allows to get all finished games. <b>If game has ended with draw it is listed at
   * the start of the array</b>
   *
   * @return List with finished games
   * @throws GamesStoreException When any error occur while getting games
   */
  List<EndedGameDto> getEndedGames() throws GamesStoreException;
}
