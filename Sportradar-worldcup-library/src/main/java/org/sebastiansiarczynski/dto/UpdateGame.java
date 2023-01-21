package org.sebastiansiarczynski.dto;

import lombok.NonNull;

public record UpdateGame(@NonNull String gameUUID, int homeScore, int awayScore) {

}
