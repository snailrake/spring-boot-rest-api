package com.webapp.testapi.service;

import com.webapp.testapi.domain.model.Format;
import com.webapp.testapi.domain.model.Song;
import com.webapp.testapi.domain.repository.SongRepository;
import com.webapp.testapi.api.dto.SongDTO;
import com.webapp.testapi.service.impl.ArtistServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    private final ArtistServiceImpl artistService;

    public List<Song> readAll() {
        return songRepository.findAll();
    }

    public List<Song> readByArtistId(Long id) {
        return songRepository.findByArtistId(id);
    }

    public Song create(SongDTO dto) {
        return songRepository.save(Song.builder()
                        .name(dto.getName())
                        .duration(dto.getDuration())
                        .size(dto.getSize())
                        .format(Format.valueOf(dto.getFormat()))
                        .artist(artistService.findById(dto.getArtistId()))
                .build());
    }

    public Song update(Song song) {
        return songRepository.save(song);
    }

    public void delete(Long id) {
        songRepository.deleteById(id);
    }

}
