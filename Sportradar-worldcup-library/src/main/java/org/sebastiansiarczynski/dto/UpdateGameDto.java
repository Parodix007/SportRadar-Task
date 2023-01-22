package org.sebastiansiarczynski.dto;

import lombok.NonNull;

public record UpdateGameDto(@NonNull String gameUUID, int homeScore, int awayScore) {

}
