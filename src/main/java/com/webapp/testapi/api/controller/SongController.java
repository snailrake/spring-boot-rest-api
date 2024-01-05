package com.webapp.testapi.api.controller;

import com.webapp.testapi.api.dto.SongDTO;
import com.webapp.testapi.api.mapper.SongMapper;
import com.webapp.testapi.service.impl.ArtistServiceImpl;
import com.webapp.testapi.service.impl.SongServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Tag(name = "Songs", description = "Allows work with songs data")
public class SongController {

    private final SongServiceImpl songService;

    private final ArtistServiceImpl artistService;

    @Operation(
            summary = "Find by artist's id",
            description = "Find all songs by artist's id"
    )
    @GetMapping("/find/{id}")
    public List<SongDTO> readByArtistId(@PathVariable @Parameter(description = "Artist id") Long id) {
        return songService.readByArtistId(id).stream()
                .map(SongMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Find all",
            description = "Find all songs"
    )
    @GetMapping
    public List<SongDTO> readAll(
            @RequestParam(required = false, defaultValue = "0") @Parameter(description = "Page number") @Min(0) int page,
            @RequestParam(required = false, defaultValue = "10") @Parameter(description = "Count of songs on page") @Min(1) @Max(100) int size
    ) {
        return songService.readAll(PageRequest.of(page, size)).stream()
                .map(SongMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Update by id",
            description = "Update song by song id"
    )
    @PutMapping("/{id}")
    public SongDTO update(
            @RequestBody @Parameter(description = "Song parameters") SongDTO songDTO,
            @PathVariable @Parameter(description = "Song id") Long id
    ) {
        songDTO.setId(id);
        return SongMapper.INSTANCE.toDTO(songService.update(SongMapper.INSTANCE.toEntity(songDTO)));
    }

    @Operation(
            summary = "Add song",
            description = "Add song to database"
    )
    @PostMapping
    public ResponseEntity<SongDTO> create(@RequestBody @Parameter(description = "Song parameters") SongDTO dto) {
        SongDTO result = SongMapper.INSTANCE.toDTO(songService.create(SongMapper.INSTANCE.toEntity(dto)));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete song",
            description = "Delete song by song id"
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Parameter(description = "Song id") Long id) {
        songService.delete(id);
    }

}
