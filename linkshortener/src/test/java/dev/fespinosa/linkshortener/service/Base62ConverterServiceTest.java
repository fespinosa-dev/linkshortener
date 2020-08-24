package dev.fespinosa.linkshortener.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Base62ConverterServiceTest {


    private Base62ConverterService base62Converter;

    @BeforeEach
    public void setup(){
        base62Converter = new Base62ConverterService();
    }

    @Test
    public void testConvert(){
      String result = base62Converter.convert(100);
      assertEquals("bM", result);
      String result2 = base62Converter.convert(50);
      assertEquals("Y", result2);

    }

    @Test
    public void testToBase(){
     double result =  base62Converter.toBase10("bM");
     assertEquals(100, result);
     double result2 =  base62Converter.toBase10("Y");
     assertEquals(50, result2);
    }

}