package com.webapp.testapi.api.exception;

public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException(Long id) {
        super(String.format(ErrorCodes.ARTIST_NOT_FOUND.getMessage(), id));
    }

}
