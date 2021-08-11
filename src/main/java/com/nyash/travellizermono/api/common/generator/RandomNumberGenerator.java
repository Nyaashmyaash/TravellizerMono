package com.nyash.travellizermono.api.common.generator;

/**
 * Returns sequential numbers using Math.random() method
 * @author Nyash
 *
 */
public class RandomNumberGenerator implements NumberGenerator{

    private final int limit;

    public RandomNumberGenerator(final int limit){
        this.limit = limit;
    }

    @Override
    public int generate() {
        return (int) (Math.random() * limit);
    }
}
