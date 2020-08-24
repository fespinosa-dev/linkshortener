package dev.fespinosa.linkshortener.service;

import dev.fespinosa.linkshortener.model.Link;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkShortenerServiceTest {

    private LinkShortenerService linkShortenerService;

    @Test
    void shorten() throws LinkAlreadyShortenedException {
        linkShortenerService.shorten(new Link());
    }



}