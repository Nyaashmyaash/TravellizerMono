package com.nyash.travellizermono.api.common.generator.text;

import java.util.UUID;

/**
 * Generates random string based on UUID format
 * @author Nyash
 *
 */
public class UUIDStringGenerator implements StringGenerator{

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
