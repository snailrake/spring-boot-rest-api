package com.webapp.testapi.service;

import com.webapp.testapi.domain.model.Artist;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ArtistService {
    Artist findById(Long id);

    List<Artist> readAll(PageRequest pageRequest);

    Artist create(Artist artist);

    Artist update(Artist artist);

    void delete(Long id);
}
