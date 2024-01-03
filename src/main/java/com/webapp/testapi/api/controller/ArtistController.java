package com.webapp.testapi.api.controller;


import com.webapp.testapi.api.dto.ArtistDTO;
import com.webapp.testapi.service.impl.ArtistServiceImpl;
import com.webapp.testapi.domain.model.Artist;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
@AllArgsConstructor
public class ArtistController {

    private final ArtistServiceImpl artistService;

    @GetMapping
    public List<ArtistDTO> readAll(
            @RequestParam(required = false, defaultValue = "0") @Min(0) int page,
            @RequestParam(required = false, defaultValue = "10") @Min(1) @Max(100) int size
    ) {
        return artistService.readAll(PageRequest.of(page, size)).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ArtistDTO update(@RequestBody ArtistDTO artistDTO, @PathVariable Long id) {
        artistDTO.setId(id);
        return entityToDto(artistService.update(dtoToEntity(artistDTO)));
    }

    @PostMapping
    public ResponseEntity<ArtistDTO> create(@RequestBody ArtistDTO dto) {
        ArtistDTO result = entityToDto(artistService.create(dtoToEntity(dto)));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        artistService.delete(id);
    }

    private Artist dtoToEntity(ArtistDTO dto) {
        return Artist.builder()
                .id(dto.getId())
                .name(dto.getName())
                .hometown(dto.getHometown())
                .birthDate(LocalDate.parse(dto.getBirthDate()))
                .build();
    }
    private ArtistDTO entityToDto(Artist artist) {
        return ArtistDTO.builder()
                .id(artist.getId())
                .name(artist.getName())
                .hometown(artist.getHometown())
                .birthDate(artist.getBirthDate().toString())
                .build();
    }
}
