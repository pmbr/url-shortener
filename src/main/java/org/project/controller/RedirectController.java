package org.project.controller;

import org.project.exception.ShortenedURLNotFoundException;
import org.project.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedirectController {

    Logger logger = LoggerFactory.getLogger(RedirectController.class);

    private Repository repository;

    public RedirectController(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/{shortenedURL}")
    public ModelAndView redirectShortenedUrl(@PathVariable String shortenedURL, ModelMap model) {
        ModelAndView modelAndView;
        try {
            String redirectURL = repository.redirectUrl(shortenedURL);
            modelAndView = new ModelAndView("redirect:" + redirectURL);
        } catch (ShortenedURLNotFoundException e) {
            model.addAttribute("errorMessage", "Attempt of redirecting using invalid shortened URL");
            model.addAttribute("shortenedURL", "");
            modelAndView = new ModelAndView("index");
            logger.error("Invalid redirect URL", e);
        }
        return modelAndView;
    }

}
