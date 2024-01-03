package com.webapp.testapi.service;

import com.webapp.testapi.api.dto.ArtistDTO;
import com.webapp.testapi.domain.model.Artist;

import java.time.LocalDate;
import java.util.List;

public interface ArtistService {
    Artist findById(Long id);

    List<Artist> readAll();

    Artist create(Artist artist);

    Artist update(Artist artist);

    void delete(Long id);
}
