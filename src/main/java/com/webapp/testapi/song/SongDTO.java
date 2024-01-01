package com.webapp.testapi.song;

import com.webapp.testapi.format.Format;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {

    private String name;

    private int duration;

    private int size;

    private Long artistId;

    private Long formatId;

}
