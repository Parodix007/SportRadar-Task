package org.sebastiansiarczynski.dto;

import java.time.ZonedDateTime;
import lombok.NonNull;
import org.sebastiansiarczynski.exception.GameCreatingException;

/**
 * DTO of the started game
 *
 * @param homeTeam  {@link String} Name of the home team
 * @param awayTeam  {@link String} Name of the away team
 * @param startDate {@link ZonedDateTime} Date of game start
 * @author Sebastian Siarczyński
 */
public record StartGameDto(String homeTeam, String awayTeam, ZonedDateTime startDate) {

  public StartGameDto(@NonNull final String homeTeam, @NonNull final String awayTeam,
      @NonNull final ZonedDateTime startDate) {
    if (homeTeam.isBlank() || awayTeam.isBlank()) {
      throw new GameCreatingException(
          "Error while creating a game! Home team or Away team name cannot be empty or null!");
    }

    if (homeTeam.equals(awayTeam)) {
      throw new GameCreatingException(
          "Error while creating a game entity! Home team and Away Team cannot be the same");
    }

    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.startDate = startDate;
  }
}
