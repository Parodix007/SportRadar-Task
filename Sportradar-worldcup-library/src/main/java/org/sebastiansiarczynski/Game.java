package org.sebastiansiarczynski;

import java.time.ZonedDateTime;
import lombok.Data;

@Data
final class Game {

  private String homeTeam;
  private String awayTeam;
  private int homeScore;
  private int awayScore;
  private ZonedDateTime startDate;
  private ZonedDateTime endDate;
}
