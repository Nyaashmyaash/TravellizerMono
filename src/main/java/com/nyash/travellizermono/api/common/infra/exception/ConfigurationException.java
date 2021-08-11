package com.nyash.travellizermono.api.common.infra.exception;


import com.nyash.travellizermono.api.common.infra.exception.base.AppException;

/**
 * Signals about incorrect configuration settings/parameters
 * @author Nyash
 *
 */
public class ConfigurationException extends AppException {

    private static final long serialVersionUID = -2177284893894040026L;

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(Throwable throwable) {
        super(throwable);
    }
}
