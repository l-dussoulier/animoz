package com.animoz.controller;

import com.animoz.model.Animal;
import com.animoz.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping (path = "/animaux")
public String AfficherListeAnimaux(Model model){

        List<Animal> listeAnimaux = animalService.getListeAnimaux();
        model.addAttribute("unNombre",42);
        model.addAttribute("message","hello world");
        model.addAttribute("listeAnimaux",listeAnimaux);
        return "listeAnimaux";

    }

    @GetMapping ( path = "/animal")
        public String affichierFormulaireCreation(@ModelAttribute("animal") AnimalDto animalDto){

        return "formAnimal";
        }

    @PostMapping( path = "/animal")
    public String traiterFormulaireCreation(@Validated @ModelAttribute("animal") AnimalDto animalDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return affichierFormulaireCreation(animalDto);
        }
        System.out.println(animalDto.getNom());
        return "accueil";
    }

    @GetMapping(path = "/findAnimal")
    public String RechercheAnimal(@RequestParam(name="filtre") String filtre, Model model){
        List<Animal> listeFiltre = animalService.getListAnimalLike(filtre);
        model.addAttribute("listeAnimaux",listeFiltre);
        System.out.println(filtre);
        return "listeAnimaux";
    }
}
