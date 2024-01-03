package com.webapp.testapi.service.impl;

import com.webapp.testapi.api.dto.ArtistDTO;
import com.webapp.testapi.api.exception.ArtistNotFoundException;
import com.webapp.testapi.api.exception.ArtistValidateException;
import com.webapp.testapi.domain.model.Artist;
import com.webapp.testapi.domain.repository.ArtistRepository;
import com.webapp.testapi.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public Artist findById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(id));
    }

    public List<Artist> readAll() {
        // TODO: доставание по частям (pageble)
        return artistRepository.findAll();
    }

    public Artist create(Artist artist) {
        validateArtist(artist);
        return artistRepository.save(Artist.builder()
                .name(artist.getName())
                .hometown(artist.getHometown())
                .birthDate(artist.getBirthDate())
                .build());
    }

    public Artist update(Artist artist) {
        if (!artistRepository.existsById(artist.getId())) {
            throw new ArtistNotFoundException(artist.getId());
        }
        validateArtist(artist);
        return artistRepository.save(artist);
    }

    public void delete(Long id) {
        artistRepository.deleteById(id);
    }

    private void validateArtist(Artist artist) {
        if (artist.getName() == null || artist.getName().isEmpty() || artist.getName().length() > 50) {
            throw new ArtistValidateException();
        }
    }

}
