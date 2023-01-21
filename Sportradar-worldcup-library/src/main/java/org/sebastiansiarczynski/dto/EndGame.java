package org.sebastiansiarczynski.dto;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NonNull;

@Getter
public final class EndGame extends GameDto {

  private final ZonedDateTime endDate;

  public EndGame(@NonNull final String homeTeam, @NonNull final String awayTeam,
      @NonNull final ZonedDateTime endDate) {
    super(homeTeam, awayTeam);

    this.endDate = endDate;
  }
}
