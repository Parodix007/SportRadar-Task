package org.sebastiansiarczynski;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Generic domain model of a game
 *
 * @author Sebastian Siarczyński
 */
abstract class Game {

  protected final String idx;
  protected final String homeTeam;
  protected final String awayTeam;

  @Getter(AccessLevel.PACKAGE)
  @Setter(AccessLevel.PACKAGE)
  private int homeScore;
  @Getter(AccessLevel.PACKAGE)
  @Setter(AccessLevel.PACKAGE)
  private int awayScore;

  Game(final String homeTeam, final String awayTeam, final int homeScore, final int awayScore) {
    this.idx = UUID.randomUUID().toString();
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeScore = homeScore;
    this.awayScore = awayScore;
  }
}
