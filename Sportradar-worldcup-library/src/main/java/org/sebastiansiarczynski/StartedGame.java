package org.sebastiansiarczynski;

import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.Getter;

final class StartedGame extends Game {

  @Getter
  private final ZonedDateTime startDate;

  StartedGame(final String homeTeam, final String awayTeam, final int homeScore,
      final int awayScore, final ZonedDateTime startDate) {
    super(homeTeam, awayTeam, homeScore, awayScore);

    this.startDate = startDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartedGame that = (StartedGame) o;
    return startDate.isEqual(that.startDate) && homeTeam.equals(that.homeTeam) && awayTeam.equals(
        that.awayTeam);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, homeTeam, awayTeam, this.getHomeScore(), this.getAwayScore());
  }
}
