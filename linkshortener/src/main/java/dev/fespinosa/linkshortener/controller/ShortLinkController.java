package dev.fespinosa.linkshortener.controller;


import dev.fespinosa.linkshortener.model.Link;
import dev.fespinosa.linkshortener.service.LinkShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ShortLinkController {

    private LinkShortenerService linkShortenerService;


    public ShortLinkController(LinkShortenerService linkShortenerService) {
        this.linkShortenerService = linkShortenerService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/0/{code}")
    public void redirect(HttpServletResponse response, @PathVariable String code) {
        String longLink = linkShortenerService.getLongLink(code);
        try {
            response.sendRedirect(longLink);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
