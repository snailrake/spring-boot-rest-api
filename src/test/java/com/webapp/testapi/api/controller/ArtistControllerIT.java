package com.webapp.testapi.api.controller;

import com.webapp.testapi.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Sql("/db/test/insert_artists_and_songs.sql")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TestConfig.class)
public class ArtistControllerIT {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    @Sql("/db/test/insert_artists_and_songs.sql")
    public void updateDB() {

    }

    @Test
    void readAll_ReturnAllArtists() throws Exception {
        String excpectedValue = getArtists();
        mockMvc.perform(MockMvcRequestBuilders.get("/artists"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(excpectedValue)
                );
    }

    @Test
    public void update_ReturnUpdatedArtist() throws Exception {
        String expectedArtist = getUpdatedArtist();
        mockMvc.perform(MockMvcRequestBuilders.put("/artists/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedArtist))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(expectedArtist)
                );
    }

    @Test
    public void create_ReturnCreatedArtist() throws Exception {
        String expectedArtist = getArtist();
        mockMvc.perform(MockMvcRequestBuilders.post("/artists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedArtist))
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(expectedArtist)
                );
    }

    @Test
    public void delete_ReturnIsOkStatus() throws Exception {
        Long id = 3L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/artists/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String getArtists() {
        return """
                [
                    {
                        "id":1,
                        "name":"John Doe",
                        "hometown":"New York",
                        "birthDate":"1980-05-15"
                    },
                    {
                        "id":2,
                        "name":"Jane Smith",
                        "hometown":"Los Angeles",
                        "birthDate":"1992-08-20"
                    },
                    {
                        "id":3,
                        "name":"Bob Johnson",
                        "hometown":"Chicago",
                        "birthDate":"1975-02-10"
                    }
                ]
                """;
    }

    private String getUpdatedArtist() {
        return """
                    {
                        "id":1,
                        "name":"John Lock",
                        "hometown":"New York",
                        "birthDate":"1980-05-15"
                    }
                """;
    }

    private String getArtist() {
        return """
                    {
                        "name":"New Artist",
                        "hometown":"New York",
                        "birthDate":"1980-05-15"
                    }
                """;
    }

}
