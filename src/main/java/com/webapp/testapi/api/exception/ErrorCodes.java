package com.webapp.testapi.api.exception;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    ARTIST_NOT_FOUND("Artist with id %d not found"),

    ARTIST_VALIDATE_EXCEPTION("Artist name length can be from 0 to 50 symbols"),

    SONG_NOT_FOUND("Song with id %d not found"),

    SONG_VALIDATE_EXCEPTION("Song name length can be from 0 to 50 symbols");


    private String message;

    ErrorCodes(String message) {
        this.message = message;
    }
}
