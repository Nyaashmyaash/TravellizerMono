package com.nyash.travellizermono.api.common.infra.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
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
     *
     * @param requestHeaderFetcher Function that accepts header name and return request header value
     * @return
     */
    public static String getUserID(final Function<String, String> requestHeaderFetcher) {
        return requestHeaderFetcher.apply(HEADER_USER_ID);
    }

    /**
     *Adds user id into headers object
     * @param headers
     * @param userId
     */
    public static void addUserId(final Map<String, List<String>> headers, final String userId) {

        Checks.checkParameter(
                !StringUtils.isBlank(userId), "User identifier should be valid string");

        headers.put(HEADER_USER_ID, List.of(userId));
    }
}
