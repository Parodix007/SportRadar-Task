package org.sebastiansiarczynski.dto;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NonNull;

@Getter
public final class StartGame extends GameDto {

  private final ZonedDateTime startDate;

  public StartGame(@NonNull final String homeTeam, @NonNull final String awayTeam,
      @NonNull final ZonedDateTime startDate) {
    super(homeTeam, awayTeam);

    this.startDate = startDate;
  }
}
