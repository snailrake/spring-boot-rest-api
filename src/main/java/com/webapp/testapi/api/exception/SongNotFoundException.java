package com.webapp.testapi.api.exception;

import java.util.UUID;

public class SongNotFoundException extends RuntimeException {

    public SongNotFoundException(UUID id) {
        super(String.format(ErrorCodes.SONG_NOT_FOUND.getMessage(), id));
    }

}
