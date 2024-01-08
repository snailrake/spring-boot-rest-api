package com.webapp.testapi.service.impl;

import com.webapp.testapi.api.exception.ArtistNotFoundException;
import com.webapp.testapi.domain.model.Artist;
import com.webapp.testapi.domain.repository.ArtistRepository;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceImplTest {

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistServiceImpl artistService;

    @Test
    public void findById_ValidArtistId_ReturnArtistById() {
        Artist artist = getArtist();
        when(artistRepository.findById(artist.getId())).thenReturn(Optional.ofNullable(artist));

        Artist result = artistService.findById(artist.getId());

        assertNotNull(result);
        assertEquals(artist, result);
    }

    @Test
    public void findById_InvalidArtistId_ThrowArtistNotFoundException() {
        UUID invalidId = UUID.randomUUID();
        doThrow(new ArtistNotFoundException(invalidId)).when(artistRepository).findById(invalidId);

        assertThrows(ArtistNotFoundException.class, () -> artistService.findById(invalidId));
    }

    @Test
    public void readAll_ReturnAllArtists() {
        List<Artist> artists = getArtists();
        Page<Artist> artistsPage = new PageImpl<>(artists);
        PageRequest pageRequest = PageRequest.of(0,2);
        when(artistRepository.findAll(pageRequest)).thenReturn(artistsPage);

        List<Artist> result = artistService.readAll(pageRequest);

        assertNotNull(result);
        assertEquals(artists, result);
    }

    @Test
    public void create_ReturnSavedArtist() {
        Artist artist = getArtist();
        when(artistRepository.save(artist)).thenReturn(artist);

        Artist result = artistService.create(artist);

        assertNotNull(result);
        assertEquals(artist, result);
    }

    @Test
    public void update_validArtistId_ReturnUpdatedArtist() {
        Artist artist = getArtist();
        when(artistRepository.existsById(artist.getId())).thenReturn(true);
        when(artistRepository.save(artist)).thenReturn(artist);

        Artist result = artistService.update(artist);

        assertNotNull(result);
        assertEquals(artist, result);
    }

    @Test
    public void update_InvalidArtistId_ThrowArtistNotFoundException() {
        Artist artist = getArtist();
        doThrow(new ArtistNotFoundException(artist.getId())).when(artistRepository).existsById(artist.getId());

        assertThrows(ArtistNotFoundException.class, () -> artistService.update(artist));
    }

    @Test
    public void delete_InvalidId_ThrowArtistNotFoundException() {
        UUID invalidId = UUID.randomUUID();
        doThrow(new ArtistNotFoundException(invalidId)).when(artistRepository).existsById(invalidId);

        assertThrows(ArtistNotFoundException.class, () -> artistService.delete(invalidId));
    }

    private Artist getArtist() {
        return Artist.builder()
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
    }

    private List<Artist> getArtists() {
        Artist firstArtist = Artist.builder()
                .id(UUID.randomUUID())
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
        Artist secondArtist = Artist.builder()
                .id(UUID.randomUUID())
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
        return List.of(firstArtist, secondArtist);
    }
}
