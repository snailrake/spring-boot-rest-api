package com.webapp.testapi.service;

import com.webapp.testapi.domain.model.Artist;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface ArtistService {
    Artist findById(UUID id);

    List<Artist> readAll(PageRequest pageRequest);

    Artist create(Artist artist);

    Artist update(Artist artist);

    void delete(UUID id);
}
