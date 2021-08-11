package com.nyash.travellizermono.api.common.infra.util;


import com.nyash.travellizermono.api.common.infra.exception.flow.InvalidParameterException;

/**
 * Contains common check routines
 * @author Nyash
 *
 */
public class Checks {
    private Checks(){
    }

    /**
     * Verifies that specified check passed and throws exception otherwise
     * @param check
     * @param message
     * @throws InvalidParameterException
     */
    public static void checkParameter(final boolean check, final String message)
            throws InvalidParameterException {
        if (!check) {
            throw new InvalidParameterException(message);
        }
    }
}
