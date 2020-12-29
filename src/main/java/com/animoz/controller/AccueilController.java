package com.animoz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @GetMapping(path = "/")
    public String acceuillir() {
        return "accueil";
    }
}
