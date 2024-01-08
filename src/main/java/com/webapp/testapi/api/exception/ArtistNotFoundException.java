package com.webapp.testapi.api.exception;

import java.util.UUID;

public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException(UUID id) {
        super(String.format(ErrorCodes.ARTIST_NOT_FOUND.getMessage(), id));
    }

}
