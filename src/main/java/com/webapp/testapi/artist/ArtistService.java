package com.webapp.testapi.artist;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public Artist findById(Long id) {
        return artistRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("Artist with id %d not found.", id)));
    }

    public List<Artist> readAll() {
        return artistRepository.findAll();
    }

    public Artist create(ArtistDTO dto) {
        return artistRepository.save(Artist.builder()
                .name(dto.getName())
                .hometown(dto.getHometown())
                .birthDate(LocalDate.parse(dto.getBirthDate()))
                .build());
    }

    public Artist update(Artist artist) {
        return artistRepository.save(artist);
    }

    public void delete(Long id) {
        artistRepository.deleteById(id);
    }

}
