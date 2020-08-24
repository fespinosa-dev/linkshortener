package dev.fespinosa.linkshortener.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Base62ConverterService {


    public static final String BASE62_ALPHABET
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


    public String convert(long number) {
        StringBuilder builder = new StringBuilder();
        long remainder  = 0;
        long dividend = number;

        while(dividend > 0) {
            remainder = dividend % 62;
            dividend = dividend / 62;
            builder.insert(0, BASE62_ALPHABET.charAt((int) remainder));

        }
        return builder.toString();
    }

    public int toBase10(String base62) {
        double result = 0;
        for (int i = base62.length(), j = 0 ; i > 0; i--, j++) {
            int index = BASE62_ALPHABET.indexOf(base62.charAt(i-1));
            result = result + (Math.pow(62, j) * index);
        }
        return (int) result;
    }
}
