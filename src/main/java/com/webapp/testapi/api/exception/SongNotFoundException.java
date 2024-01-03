package com.webapp.testapi.api.exception;

public class SongNotFoundException extends RuntimeException {

    public SongNotFoundException(Long id) {
        super(String.format(ErrorCodes.SONG_NOT_FOUND.getMessage(), id));
    }

}
