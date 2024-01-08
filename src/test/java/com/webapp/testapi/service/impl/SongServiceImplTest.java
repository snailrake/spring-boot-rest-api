package com.webapp.testapi.service.impl;

import com.webapp.testapi.api.exception.ArtistNotFoundException;
import com.webapp.testapi.api.exception.SongNotFoundException;
import com.webapp.testapi.domain.model.Artist;
import com.webapp.testapi.domain.model.Format;
import com.webapp.testapi.domain.model.Song;
import com.webapp.testapi.domain.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SongServiceImplTest {

    @Mock
    SongRepository songRepository;

    @Mock
    ArtistServiceImpl artistService;

    @InjectMocks
    SongServiceImpl songService;

    @Test
    public void readAll_ReturnAllSongs() {
        List<Song> songs = getSongs();
        Page<Song> songsPage = new PageImpl<>(songs);
        PageRequest pageRequest = PageRequest.of(0, 2);
        when(songRepository.findAll(pageRequest)).thenReturn(songsPage);

        List<Song> result = songService.readAll(pageRequest);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertIterableEquals(songs, result);
    }

    @Test
    public void readByArtistId_ValidArtistId_ReturnSongsByArtistId() {
        UUID artistId = UUID.randomUUID();
        Artist artist = getArtist();
        List<Song> songs = getSongs();
        when(artistService.findById(artistId)).thenReturn(artist);
        when(songRepository.findByArtistId(artistId)).thenReturn(songs);

        List<Song> result = songService.readByArtistId(artistId);

        assertNotNull(result);
        assertEquals(2, songs.size());
        assertIterableEquals(songs, result);
    }

    @Test
    public void readByArtistId_InvalidArtistId_ThrowArtistNotFoundException() {
        UUID invalidId = UUID.randomUUID();
        doThrow(new ArtistNotFoundException(invalidId)).when(artistService).findById(invalidId);

        assertThrows(ArtistNotFoundException.class, () -> songService.readByArtistId(invalidId));
    }

    @Test
    public void create_ReturnSavedSong() {
        Song song = getSong();
        when(songRepository.save(song)).thenReturn(song);

        Song result = songService.create(song);

        assertNotNull(result);
        assertEquals(song, result);
    }

    @Test
    public void update_ValidSongId_ReturnUpdatedSong() {
        Song song = getSong();
        when(songRepository.existsById(song.getId())).thenReturn(true);
        when(songRepository.save(song)).thenReturn(song);

        Song result = songService.update(song);

        assertNotNull(result);
        assertEquals(song, result);
    }

    @Test
    public void update_InvalidSongId_ThrowSongNotFoundException() {
        Song song = getSong();
        doThrow(new SongNotFoundException(song.getId())).when(songRepository).existsById(song.getId());

        assertThrows(SongNotFoundException.class, () -> songService.update(song));
    }

    @Test
    public void delete_InvalidId_ThrowSongNotFoundException() {
        UUID invalidId = UUID.randomUUID();
        doThrow(new SongNotFoundException(invalidId)).when(songRepository).existsById(invalidId);

        assertThrows(SongNotFoundException.class, () -> songService.delete(invalidId));
    }

    private List<Song> getSongs() {
        Artist artist = Artist.builder()
                .id(UUID.randomUUID())
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
        Song firstSong = Song.builder()
                .id(UUID.randomUUID())
                .name("First song")
                .duration(180)
                .size(1024)
                .format(Format.MP3)
                .artist(artist)
                .build();
        Song secondSong = Song.builder()
                .id(UUID.randomUUID())
                .name("Second song")
                .duration(260)
                .size(2048)
                .format(Format.MP3)
                .artist(artist)
                .build();
        return List.of(firstSong, secondSong);
    }

    private Song getSong() {
        Artist artist = Artist.builder()
                .id(UUID.randomUUID())
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
        return Song.builder()
                .name("First song")
                .duration(180)
                .size(1024)
                .format(Format.MP3)
                .artist(artist)
                .build();
    }

    private Artist getArtist() {
        return Artist.builder()
                .id(UUID.randomUUID())
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
    }
}
