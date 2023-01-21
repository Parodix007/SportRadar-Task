package org.sebastiansiarczynski.dto;

import lombok.Getter;
import org.sebastiansiarczynski.exception.GameCreatingException;

@Getter
abstract class GameDto {

  protected final String homeTeam;
  protected final String awayTeam;

  GameDto(final String homeTeam, final String awayTeam) {
    if (homeTeam.equals(awayTeam)) {
      throw new GameCreatingException(
          "Error while creating a game entity! Home team and Away Team cannot be the same");
    }

    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
  }
}
