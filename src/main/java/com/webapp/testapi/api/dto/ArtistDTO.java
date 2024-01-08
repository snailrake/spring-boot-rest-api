package com.webapp.testapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Artist entity")
public class ArtistDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Artist name")
    private String name;

    @Schema(description = "Artist hometown")
    private String hometown;

    @Schema(description = "Artist birthdate")
    private String birthDate;

}
