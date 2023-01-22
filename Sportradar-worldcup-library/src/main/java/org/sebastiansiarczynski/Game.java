package org.sebastiansiarczynski;

import lombok.Getter;
import lombok.Setter;

abstract class Game {

  protected final String homeTeam;
  protected final String awayTeam;

  @Getter
  @Setter
  private int homeScore;
  @Getter
  @Setter
  private int awayScore;

  Game(final String homeTeam, final String awayTeam, final int homeScore, final int awayScore) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeScore = homeScore;
    this.awayScore = awayScore;
  }
}
