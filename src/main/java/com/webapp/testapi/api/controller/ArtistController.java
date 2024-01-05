package com.webapp.testapi.api.controller;


import com.webapp.testapi.api.dto.ArtistDTO;
import com.webapp.testapi.api.mappers.ArtistMapper;
import com.webapp.testapi.service.impl.ArtistServiceImpl;
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
@RequestMapping("/artists")
@AllArgsConstructor
@Tag(name = "Artists", description = "Allows work with artists data")
public class ArtistController {

    private final ArtistServiceImpl artistService;

    @Operation(
            summary = "Find all",
            description = "Find all artists"
    )
    @GetMapping
    public List<ArtistDTO> readAll(
            @RequestParam(required = false, defaultValue = "0") @Parameter(description = "Number of page") @Min(0) int page,
            @RequestParam(required = false, defaultValue = "10") @Parameter(description = "Count of artists on page") @Min(1) @Max(100) int size
    ) {
        return artistService.readAll(PageRequest.of(page, size)).stream()
                .map(ArtistMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Update by id",
            description = "Update artist by artist id"
    )
    @PutMapping("/{id}")
    public ArtistDTO update(
            @RequestBody @Parameter(description = "Artist parameters") ArtistDTO artistDTO,
            @PathVariable @Parameter(description = "Artist id") Long id
    ) {
        artistDTO.setId(id);
        return ArtistMapper.INSTANCE.toDTO(artistService.update(ArtistMapper.INSTANCE.toEntity(artistDTO)));
    }

    @Operation(
            summary = "Add artist",
            description = "Add artist to database"
    )
    @PostMapping
    public ResponseEntity<ArtistDTO> create(@RequestBody @Parameter(description = "Artist parameters") ArtistDTO dto) {
        ArtistDTO result = ArtistMapper.INSTANCE.toDTO(artistService.create(ArtistMapper.INSTANCE.toEntity(dto)));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete by id",
            description = "Delete artist by artist id"
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Parameter(description = "Artist id") Long id) {
        artistService.delete(id);
    }

}
