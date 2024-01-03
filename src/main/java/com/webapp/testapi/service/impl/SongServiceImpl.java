package com.webapp.testapi.service.impl;

import com.webapp.testapi.api.exception.SongNotFoundException;
import com.webapp.testapi.api.exception.SongValidateException;
import com.webapp.testapi.domain.model.Format;
import com.webapp.testapi.domain.model.Song;
import com.webapp.testapi.domain.repository.SongRepository;
import com.webapp.testapi.api.dto.SongDTO;
import com.webapp.testapi.service.SongService;
import com.webapp.testapi.service.impl.ArtistServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    private final ArtistServiceImpl artistService;

    public List<Song> readAll(PageRequest pageRequest) {
        Page<Song> page = songRepository.findAll(pageRequest);
        return page.getContent();
    }

    public List<Song> readByArtistId(Long id) {
        return songRepository.findByArtistId(id);
    }

    public Song create(Song song) {
        validateSong(song);
        return songRepository.save(Song.builder()
                        .name(song.getName())
                        .duration(song.getDuration())
                        .size(song.getSize())
                        .format(song.getFormat())
                        .artist(song.getArtist())
                .build());
    }

    public Song update(Song song) {
        if(!songRepository.existsById(song.getId())) {
            throw new SongNotFoundException(song.getId());
        }
        validateSong(song);
        return songRepository.save(song);
    }

    public void delete(Long id) {
        songRepository.deleteById(id);
    }

    private void validateSong(Song song) {
        if (song.getName() == null || song.getName().isEmpty() || song.getName().length() > 50) {
            throw new SongValidateException();
        }
    }

}
