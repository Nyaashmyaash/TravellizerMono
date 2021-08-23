package com.nyash.travellizermono.api.common.infra.util;

import java.util.function.Function;

/**
 * Shared security routines
 *
 * @author Nyash
 */
public class SecurityUtil {

    private static final String HEADER_USER_ID = "X-USER-ID";

    /**
     * Extract user id value from HTTP request
     * @param requestHeaderFetcher Function that accepts header name and return request header value
     * @return
     */
    public static String getUserID(final Function<String, String> requestHeaderFetcher) {
        return requestHeaderFetcher.apply(HEADER_USER_ID);
    }
}
