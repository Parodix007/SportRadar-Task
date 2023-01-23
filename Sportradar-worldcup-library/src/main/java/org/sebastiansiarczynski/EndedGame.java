package org.sebastiansiarczynski;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * Domain model of a finished game
 *
 * @author Sebastian Siarczyński
 */
final class EndedGame extends Game {

  @Getter(AccessLevel.PACKAGE)
  private final ZonedDateTime endDate;
  @Getter(AccessLevel.PACKAGE)
  private final ZonedDateTime startDate;
  @Getter(AccessLevel.PACKAGE)
  private final Period gameLength;

  EndedGame(final String homeTeam, final String awayTeam, final int homeScore, final int awayScore,
      final ZonedDateTime endDate, final ZonedDateTime startDate) {
    super(homeTeam, awayTeam, homeScore, awayScore);

    this.endDate = endDate;
    this.startDate = startDate;
    this.gameLength = Period.between(endDate.toLocalDate(), startDate.toLocalDate());
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
        endedGame.awayTeam) && startDate.isEqual(endedGame.startDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endDate, homeTeam, awayTeam, this.getHomeScore(), this.getAwayScore(),
        startDate);
  }
}
