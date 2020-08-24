package dev.fespinosa.linkshortener.controller;

import dev.fespinosa.linkshortener.model.Link;
import dev.fespinosa.linkshortener.service.LinkShortenerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private LinkShortenerService linkShortenerService;

    public AdminController(LinkShortenerService linkShortenerService) {
        this.linkShortenerService = linkShortenerService;
    }

    @GetMapping("/admin")
    public String getHomePage(Link link, Model model){
        model.addAttribute("shortLinks", linkShortenerService.getShortLinks());
        return "admin/admin";
    }

}
