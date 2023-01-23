package org.sebastiansiarczynski;

import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;

final class EndedGame extends Game {

  @Getter(AccessLevel.PACKAGE)
  private final ZonedDateTime endDate;

  EndedGame(final String homeTeam, final String awayTeam, final int homeScore, final int awayScore,
      final ZonedDateTime endDate) {
    super(homeTeam, awayTeam, homeScore, awayScore);

    this.endDate = endDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EndedGame endedGame = (EndedGame) o;
    return endDate.isEqual(endedGame.endDate) && homeTeam.equals(endedGame.homeTeam)
        && awayTeam.equals(
        endedGame.awayTeam);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endDate, homeTeam, awayTeam, this.getHomeScore(), this.getAwayScore());
  }
}
