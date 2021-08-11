package com.nyash.travellizermono.api.common.infra.exception.flow;


import com.nyash.travellizermono.api.common.infra.exception.base.AppException;

public class InvalidParameterException extends AppException {

    private static final long serialVersionUID = 4990959228756992926L;

    public InvalidParameterException(String message) {
        super(message);
    }
}
