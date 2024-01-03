package com.webapp.testapi.api.controller;

import com.webapp.testapi.api.dto.SongDTO;
import com.webapp.testapi.domain.model.Format;
import com.webapp.testapi.domain.model.Song;
import com.webapp.testapi.service.impl.ArtistServiceImpl;
import com.webapp.testapi.service.impl.SongServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {

    private final SongServiceImpl songService;

    private final ArtistServiceImpl artistService;

    @GetMapping("/find/{id}")
    public List<SongDTO> readByArtistId(@PathVariable Long id) {
        return songService.readByArtistId(id).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<SongDTO> readAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return songService.readAll(PageRequest.of(page, size)).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public SongDTO update(@RequestBody SongDTO songDTO, @PathVariable Long id) {
        songDTO.setId(id);
        return entityToDto(songService.update(dtoToEntity(songDTO)));
    }

    @PostMapping
    public ResponseEntity<SongDTO> create(@RequestBody SongDTO dto) {
        SongDTO result = entityToDto(songService.create(dtoToEntity(dto)));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        songService.delete(id);
    }

    private Song dtoToEntity(SongDTO dto) {
        return Song.builder()
                .id(dto.getId())
                .name(dto.getName())
                .duration(dto.getDuration())
                .size(dto.getSize())
                .format(Format.valueOf(dto.getFormat()))
                .artist(artistService.findById(dto.getArtistId()))
                .build();
    }

    private SongDTO entityToDto(Song song) {
        return SongDTO.builder()
                .id(song.getId())
                .name(song.getName())
                .duration(song.getDuration())
                .size(song.getSize())
                .format(song.getFormat().name())
                .artistId(song.getArtist().getId())
                .build();
    }

}
