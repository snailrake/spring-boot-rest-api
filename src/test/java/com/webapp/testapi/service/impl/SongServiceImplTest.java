package com.webapp.testapi.service.impl;

import com.webapp.testapi.api.exception.SongNotFoundException;
import com.webapp.testapi.domain.model.Artist;
import com.webapp.testapi.domain.model.Format;
import com.webapp.testapi.domain.model.Song;
import com.webapp.testapi.domain.repository.SongRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        PageRequest pageRequest = PageRequest.of(0, 3);
        Mockito.when(songRepository.findAll(pageRequest)).thenReturn(songsPage);

        List<Song> result = songService.readAll(pageRequest);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(songs.get(0), result.get(0));
        assertEquals(songs.get(1), result.get(1));
        assertEquals(songs.get(2), result.get(2));
    }

    @Test
    public void readByArtistId_ReturnSongsByArtistId() {
        Long id = 2L;
        List<Song> songs = List.of(getSongs().get(1));
        Mockito.when(artistService.findById(id)).thenReturn(getArtist());
        Mockito.when(songService.readByArtistId(id)).thenReturn(List.of(getSongs().get(1)));

        List<Song> result = songService.readByArtistId(id);

        assertNotNull(result);
        assertEquals(songs.get(0), result.get(0));
    }

    @Test
    public void create_ReturnSavedSong() {
        Song song = getSong();
        Mockito.when(songRepository.save(song)).thenReturn(song);

        Song result = songService.create(song);

        assertNotNull(result);
        assertEquals(song, result);
    }

    @Test
    public void update_ReturnUpdatedSong() {
        Song song = getSong();
        Mockito.when(songRepository.existsById(song.getId())).thenReturn(true);
        Mockito.when(songRepository.save(song)).thenReturn(song);

        Song result = songService.update(song);

        assertNotNull(result);
        assertEquals(song, result);
    }

    @Test
    public void delete_InvalidId_ThrowSongNotFoundException() {
        Long invalidId = 2L;
        Mockito.doThrow(new SongNotFoundException(invalidId)).when(songRepository).existsById(invalidId);

        assertThrows(SongNotFoundException.class, () -> songService.delete(invalidId));
    }

    private Artist getArtist() {
        return Artist.builder()
                .id(2L)
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
    }

    private List<Song> getSongs() {
        Artist firstArtist = Artist.builder()
                .id(1L)
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();

        Artist secondArtist = Artist.builder()
                .id(2L)
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();

        Song firstSong = Song.builder()
                .id(1L)
                .name("Song1")
                .duration(180)
                .size(1024)
                .format(Format.MP3)
                .artist(firstArtist)
                .build();

        Song secondSong = Song.builder()
                .id(2L)
                .name("Song2")
                .duration(200)
                .size(2048)
                .format(Format.WAV)
                .artist(secondArtist)
                .build();

        Song thirdSong = Song.builder()
                .id(3L)
                .name("Song3")
                .duration(150)
                .size(512)
                .format(Format.MP3)
                .artist(firstArtist)
                .build();

        return List.of(firstSong, secondSong, thirdSong);
    }

    private Song getSong() {
        Artist artist = Artist.builder()
                .id(1L)
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();

        return Song.builder()
                .name("Song1")
                .duration(180)
                .size(1024)
                .format(Format.MP3)
                .artist(artist)
                .build();
    }

    private Song getSongWithId() {
        Artist artist = Artist.builder()
                .id(1L)
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();

        return Song.builder()
                .id(2L)
                .name("Song2")
                .duration(200)
                .size(2048)
                .format(Format.WAV)
                .artist(artist)
                .build();
    }

}
