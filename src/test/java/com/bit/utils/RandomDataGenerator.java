package com.bit.utils;

import lombok.Getter;

import java.util.Random;
@Getter

public class RandomDataGenerator{
    private static final Random random = new Random();


    public static int generateRandomNumber(){
        return random.nextInt();
    }

    public static String generateRandomString(int length){
        int leftLimit = 97;
        int rightLimit = 122;
        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
}
