package com.webapp.testapi.service.impl;

import com.webapp.testapi.domain.model.Format;
import com.webapp.testapi.domain.model.Song;
import com.webapp.testapi.domain.repository.SongRepository;
import com.webapp.testapi.api.dto.SongDTO;
import com.webapp.testapi.service.SongService;
import com.webapp.testapi.service.impl.ArtistServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    private final ArtistServiceImpl artistService;

    public List<Song> readAll() {
        return songRepository.findAll();
    }

    public List<Song> readByArtistId(Long id) {
        return songRepository.findByArtistId(id);
    }

    public Song create(Song song) {
        return songRepository.save(Song.builder()
                        .name(song.getName())
                        .duration(song.getDuration())
                        .size(song.getSize())
                        .format(song.getFormat())
                        .artist(song.getArtist())
                .build());
    }

    public Song update(Song song) {
        return songRepository.save(song);
    }

    public void delete(Long id) {
        songRepository.deleteById(id);
    }

}
