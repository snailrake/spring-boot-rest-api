package com.webapp.testapi.song;

import com.webapp.testapi.artist.ArtistService;
import com.webapp.testapi.format.FormatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    private final FormatService formatService;

    private final ArtistService artistService;

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
                        .format(formatService.findById(dto.getFormatId()))
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
