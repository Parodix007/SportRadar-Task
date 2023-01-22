package org.sebastiansiarczynski.dto;

import java.time.ZonedDateTime;
import lombok.NonNull;
import org.sebastiansiarczynski.exception.GameCreatingException;

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
