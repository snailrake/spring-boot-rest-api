package com.webapp.testapi.api.exception;

public class ArtistValidateException extends RuntimeException {

    public ArtistValidateException() {
        super(ErrorCodes.ARTIST_VALIDATE_EXCEPTION.getMessage());
    }

}
