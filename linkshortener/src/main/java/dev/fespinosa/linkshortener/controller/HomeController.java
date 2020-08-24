package dev.fespinosa.linkshortener.controller;

import dev.fespinosa.linkshortener.model.Link;
import dev.fespinosa.linkshortener.service.LinkAlreadyShortenedException;
import dev.fespinosa.linkshortener.service.LinkShortenerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HomeController {

    private LinkShortenerService linkShortenerService;

    public HomeController(LinkShortenerService linkShortenerService) {
        this.linkShortenerService = linkShortenerService;
    }

    @GetMapping("/home")
    public String getHomePage(Link link, Model model){
        model.addAttribute("shortLinks", linkShortenerService.getShortLinks());
        return "index";
    }


    @PostMapping("/shortener")
    public String shorten(@ModelAttribute("link") Link link, Model model){
        try {
            linkShortenerService.shorten(link);
        } catch (LinkAlreadyShortenedException e) {

        }
        return "redirect:/home";

    }

    
}
