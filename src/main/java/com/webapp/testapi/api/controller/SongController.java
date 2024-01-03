package com.webapp.testapi.api.controller;

import com.webapp.testapi.domain.model.Song;
import com.webapp.testapi.api.dto.SongDTO;
import com.webapp.testapi.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping("/find/{id}")
    public ResponseEntity<List<Song>> readById(@PathVariable Long id) {
        return new ResponseEntity<>(songService.readByArtistId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Song>> readAll() {
        List<Song> songs = songService.readAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Song> update(@RequestBody Song song) {
        return new ResponseEntity<>(songService.update(song), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Song> create(@RequestBody SongDTO dto) {
        return new ResponseEntity<>(songService.create(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}