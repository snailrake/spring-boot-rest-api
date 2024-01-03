package com.webapp.testapi.api.exception;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    ARTIST_NOT_FOUND("Artist with id %d not found");

    private String message;

    ErrorCodes(String message) {
        this.message = message;
    }
}
