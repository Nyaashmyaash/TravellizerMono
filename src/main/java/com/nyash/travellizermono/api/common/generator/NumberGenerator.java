package com.nyash.travellizermono.api.common.generator;

/**
 * Process that generates random number
 * @author Nyash
 *
 */
public interface NumberGenerator {
    /**
     * Returns a unique number that should differ from previously generated numbers
     * @return
     */
    int generate();

}
