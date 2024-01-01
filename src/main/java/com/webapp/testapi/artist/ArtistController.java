package com.webapp.testapi.artist;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
@AllArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<Artist>> readAll() {
        return new ResponseEntity<>(artistService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Artist> update(@RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.update(artist), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Artist> create(@RequestBody ArtistDTO dto) {
        return new ResponseEntity<>(artistService.create(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        artistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
