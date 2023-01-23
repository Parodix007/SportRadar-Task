package org.sebastiansiarczynski;

import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import org.sebastiansiarczynski.dto.StartGameDto;

public final class StartedGame extends Game {

  @Getter(AccessLevel.PACKAGE)
  private final ZonedDateTime startDate;

  StartedGame(final String homeTeam, final String awayTeam, final int homeScore,
      final int awayScore, final ZonedDateTime startDate) {
    super(homeTeam, awayTeam, homeScore, awayScore);

    this.startDate = startDate;
  }

  public static StartedGame fromStartedGameDto(@NonNull final StartGameDto startGameDto) {
    return new StartedGame(startGameDto.homeTeam(), startGameDto.awayTeam(), 0, 0,
        startGameDto.startDate());
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
