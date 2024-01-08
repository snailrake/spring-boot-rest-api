package com.webapp.testapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Song entity")
public class SongDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Song name")
    private String name;

    @Schema(description = "Song duration in seconds")
    private int duration;

    @Schema(description = "Song size in kilobytes")
    private int size;

    @Schema(description = "Artist id")
    private UUID artistId;

    @Schema(description = "Song file format", example = "MP3")
    private String format;

}
