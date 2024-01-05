package com.webapp.testapi.service.impl;

import com.webapp.testapi.api.exception.ArtistNotFoundException;
import com.webapp.testapi.domain.model.Artist;
import com.webapp.testapi.domain.repository.ArtistRepository;
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
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceImplTest {

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistServiceImpl artistService;

    @Test
    public void findById_ReturnArtistById() {
        Long id = 2L;
        Artist artist = getArtistWithId();
        Mockito.when(artistRepository.findById(2L)).thenReturn(Optional.ofNullable(artist));

        Artist result = artistService.findById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, artist.getId());
    }

    @Test
    public void readAll_ReturnAllArtists() {
        List<Artist> artists = getArtists();
        Page<Artist> artistPage = new PageImpl<>(artists);
        PageRequest pageRequest = PageRequest.of(0, 3);
        Mockito.when(artistRepository.findAll(pageRequest)).thenReturn(artistPage);

        List<Artist> result = artistService.readAll(pageRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(artists.get(0), result.get(0));
        Assertions.assertEquals(artists.get(1), result.get(1));
        Assertions.assertEquals(artists.get(2), result.get(2));
    }

    @Test
    public void create_ReturnSavedArtist() {
        Artist artist = getArtist();
        Mockito.when(artistRepository.save(artist)).thenReturn(artist);

        Artist result = artistService.create(artist);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(artist.getId(), result.getId());
        Assertions.assertEquals(artist.getName(), result.getName());
        Assertions.assertEquals(artist.getHometown(), result.getHometown());
        Assertions.assertEquals(artist.getBirthDate(), result.getBirthDate());
    }

    @Test
    public void update_ReturnUpdatedArtist() {
        Artist artist = getArtistWithId();
        Mockito.when(artistRepository.existsById(artist.getId())).thenReturn(true);
        Mockito.when(artistRepository.save(artist)).thenReturn(artist);

        Artist result = artistService.update(artist);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(artist.getId(), result.getId());
        Assertions.assertEquals(artist.getName(), result.getName());
        Assertions.assertEquals(artist.getHometown(), result.getHometown());
        Assertions.assertEquals(artist.getBirthDate(), result.getBirthDate());
    }

    @Test
    public void delete_InvalidId_ThrowArtistNotFoundException() {
        Long invalidId = 2L;
        Mockito.doThrow(new ArtistNotFoundException(invalidId)).when(artistRepository).existsById(invalidId);

        Assertions.assertThrows(ArtistNotFoundException.class, () -> artistService.delete(invalidId));
    }

    private List<Artist> getArtists() {
        Artist firstArtist = Artist.builder()
                .id(1L)
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
        Artist secondArtist = Artist.builder()
                .id(2L)
                .name("Hook")
                .hometown("Texas")
                .birthDate(LocalDate.parse("1996-10-01"))
                .build();
        Artist thirdArtist = Artist.builder()
                .id(3L)
                .name("Pasha")
                .hometown("New-York")
                .birthDate(LocalDate.parse("1987-11-08"))
                .build();
        return List.of(firstArtist, secondArtist, thirdArtist);
    }

    private Artist getArtist() {
        return Artist.builder()
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
    }

    private Artist getArtistWithId() {
        return Artist.builder()
                .id(2L)
                .name("Jane")
                .hometown("Alabama")
                .birthDate(LocalDate.parse("1999-10-12"))
                .build();
    }

}