package org.sebastiansiarczynski.dto;

import java.time.ZonedDateTime;

/**
 * DTO of a finished game
 *
 * @param homeTeam  {@link String} Name of the home team
 * @param awayTeam  {@link String} Name of the away team
 * @param homeScore {@link Integer} Score of the home team
 * @param awayScore {@link Integer} Score of the away team
 * @param endDate   {@link ZonedDateTime} Date of game finish
 * @author Sebastian Siarczyński
 */
public record EndedGameDto(String homeTeam, String awayTeam, int homeScore, int awayScore,
                           ZonedDateTime endDate) {

}
