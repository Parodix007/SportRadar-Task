package org.sebastiansiarczynski.dto;

import java.time.ZonedDateTime;

public record EndedGameDto(String homeTeam, String awayTeam, int homeScore, int awayScore,
                        ZonedDateTime endDate) {

}
