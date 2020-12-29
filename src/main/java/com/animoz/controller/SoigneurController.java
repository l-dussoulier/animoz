package com.animoz.controller;

import com.animoz.model.Soigneur;
import com.animoz.service.SoigneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SoigneurController {

    @Autowired
    private SoigneurService soigneurService;

    @GetMapping(path = "/soigneurs")
    public String AfficherListeSoigneur(Model model) {
        List<Soigneur> listeSoigneur = soigneurService.getListeSoigneur();
        model.addAttribute("listeSoigneur", listeSoigneur);
        return "listeSoigneurs";

    }

    @GetMapping(path = "/soigneur")
    public String affichierFormulaireCreation(@ModelAttribute("soigneur") SoigneurDto soigneurDto) {

        return "AddSoigneur";
    }

    @PostMapping(path = "/soigneur")
    public String AjouterSoigneur(@Validated @ModelAttribute("soigneur") SoigneurDto soigneurDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return affichierFormulaireCreation(soigneurDto);
        }
        System.out.println(soigneurDto.getNom());
        System.out.println(soigneurDto.getNumero());
        Soigneur soigneur = soigneurService.addSoigneur(soigneurDto);

        return "accueil";
    }
}