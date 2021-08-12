package com.nyash.travellizermono.api.common.infra.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringChecker {

    public void checkOnEmpty (@NonNull String value,
                              @NonNull String fieldName) {

        if (value.trim().isEmpty()) {
            throw new BadRequestException(String.format("Field with name \"%s\" cannot be empty", fieldName));
        }
    }
}
