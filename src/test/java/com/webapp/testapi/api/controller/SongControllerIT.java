package com.webapp.testapi.api.controller;

import com.webapp.testapi.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Sql("/db/test/insert_artists_and_songs.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestConfig.class)
public class SongControllerIT {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    @Sql("/db/test/insert_artists_and_songs.sql")
    public void updateDB() {

    }

    @Test
    public void readByArtistId_ReturnSongsByArtistId() throws Exception {
        UUID artistId = UUID.fromString("ec7f6150-be8b-428a-a11d-e51f69d1b0a4");
        String expectedSong = getSong();
        mockMvc.perform(MockMvcRequestBuilders.get("/songs/artist/{id}", artistId))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(expectedSong)
                );
    }

    @Test
    public void readAll_ReturnAllSongs() throws Exception {
        String excpectedSongs = getSongs();
        mockMvc.perform(MockMvcRequestBuilders.get("/songs"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(excpectedSongs)
                );
    }

    @Test
    public void update_ReturnUpdatedSong() throws Exception {
        UUID songId = UUID.fromString("cfb5453b-9ded-48f1-a87f-0a6da75ad2d7");
        String expectedSong = getUpdatedSong();
        mockMvc.perform(MockMvcRequestBuilders.put("/songs/{id}", songId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedSong))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(expectedSong)
                );
    }

    @Test
    public void create_ReturnCreatedSong() throws Exception {
        String expectedSong = getSongForCreate();
        mockMvc.perform(MockMvcRequestBuilders.post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedSong))
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(expectedSong)
                );
    }

    @Test
    public void delete_ReturnIsOkStatus() throws Exception {
        UUID id = UUID.fromString("cfb5453b-9ded-48f1-a87f-0a6da75ad2d7");
        mockMvc.perform(MockMvcRequestBuilders.delete("/songs/{id}", id))
                .andExpect(status().isOk());
    }

    private String getSongs() {
        return """
                [
                    {
                        "id":"cfb5453b-9ded-48f1-a87f-0a6da75ad2d7",
                        "name":"Song 1",
                        "duration":180,
                        "size":1024,
                        "artistId":"ec7f6150-be8b-428a-a11d-e51f69d1b0a4",
                        "format":"MP3"
                    },
                    {
                        "id":"48e22cdd-3ec3-48d0-9327-0e0267f09501",
                        "name":"Song 2",
                        "duration":240,
                        "size":2048,
                        "artistId":"07f1d6a1-6b9b-4ae2-9736-5b35869ba828",
                        "format":"FLAC"
                    }
                ]
                """;
    }

    private String getUpdatedSong() {
        return """
                {
                    "id":"cfb5453b-9ded-48f1-a87f-0a6da75ad2d7",
                    "name":"Updated Song",
                    "duration":200,
                    "size":1536,
                    "artistId":"ec7f6150-be8b-428a-a11d-e51f69d1b0a4",
                    "format":"MP3"
                }
                """;
    }

    private String getSongForCreate() {
        return """
                {
                    "name":"New Song",
                    "duration":180,
                    "size":1024,
                    "artistId":"ec7f6150-be8b-428a-a11d-e51f69d1b0a4",
                    "format":"WAV"
                }
                """;
    }

    private String getSong() {
        return """
                [
                    {
                        "id":"cfb5453b-9ded-48f1-a87f-0a6da75ad2d7",
                        "name":"Song 1",
                        "duration":180,
                        "size":1024,
                        "artistId":"ec7f6150-be8b-428a-a11d-e51f69d1b0a4",
                        "format":"MP3"
                    }
                ]
                """;
    }

}
