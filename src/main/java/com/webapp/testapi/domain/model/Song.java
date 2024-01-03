package com.webapp.testapi.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "songs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private int duration;

    private int size;

    @Enumerated(EnumType.STRING)
    private Format format;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

}
