package com.nyash.travellizermono.api.common.infra.exception;

import com.nyash.travellizermono.api.common.infra.exception.base.AppException;

/**
 * Base class for all authentication cases in the system
 * @author Nyash
 *
 */
public class AuthenticationException extends AppException {

    private static final long serialVersionUID = 9125254351678473266L;

    public AuthenticationException(String message) {
        super(message);
    }
}
