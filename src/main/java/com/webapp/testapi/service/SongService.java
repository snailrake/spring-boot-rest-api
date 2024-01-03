package com.webapp.testapi.service;

import com.webapp.testapi.api.dto.SongDTO;
import com.webapp.testapi.domain.model.Format;
import com.webapp.testapi.domain.model.Song;

import java.util.List;

public interface SongService {
    List<Song> readAll();

    List<Song> readByArtistId(Long id);

    Song create(Song song);

    Song update(Song song);

    void delete(Long id);
}
