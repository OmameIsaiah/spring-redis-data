package com.redis.data.utils;

import java.util.Random;

public class Utils {
    public static Long getRandomID() {
        Random random = new Random();
        // Generate a random number between 10000 and 99999 (inclusive)
        return random.nextLong(90000) + 10000;
    }
}
