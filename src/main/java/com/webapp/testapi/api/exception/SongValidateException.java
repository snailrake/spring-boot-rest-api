package com.webapp.testapi.api.exception;

public class SongValidateException extends RuntimeException {

    public SongValidateException() {
        super(ErrorCodes.SONG_VALIDATE_EXCEPTION.getMessage());
    }

}
