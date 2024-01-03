package com.webapp.testapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {

    private String name;

    private int duration;

    private int size;

    private Long artistId;

    private String format;

}
