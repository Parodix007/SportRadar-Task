package org.sebastiansiarczynski.dto;

import lombok.NonNull;

/**
 * DTO of game update
 *
 * @param gameUUID  {@link String} Uniq uuid of started game
 * @param homeScore {@link Integer} Score of the home team
 * @param awayScore {@link Integer} Score of the away team
 * @author Sebastian Siarczyński
 */
public record UpdateGameDto(@NonNull String gameUUID, int homeScore, int awayScore) {

}
