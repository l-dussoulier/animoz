package com.animoz.controller;

import com.animoz.model.Animal;
import com.animoz.model.Soigneur;
import com.animoz.service.SoigneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Soigneur soigneur = soigneurService.addSoigneur(soigneurDto);
        return "redirect:/soigneurs";
    }

    @GetMapping(path = "/findSoigneur")
    public String RechercheSoigneur(@RequestParam(name="filtre") String filtre, Model model){
        List<Soigneur> listeFiltre = soigneurService.getListSoigneurLike(filtre);
        model.addAttribute("listeSoigneurs",listeFiltre);
        return "listeSoigneurs";
    }

    @GetMapping(path ="/addSoigneur/{idSoigneur}")
    public String addSoigneurAnimal(Model model,@PathVariable Long idSoigneur){
        List<Soigneur> liste = soigneurService.getListeSoigneur();
        model.addAttribute("listeSoigneursAdd",liste);
        return "listeSoigneursAdd";
    }
}
