package com.animoz.controller;

import com.animoz.model.Animal;
import com.animoz.model.Espece;
import com.animoz.model.Regime;
import com.animoz.model.Soigneur;
import com.animoz.service.AnimalNonTrouveeExeption;
import com.animoz.service.AnimalService;
import com.animoz.service.SoigneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.validation.ValidationUtils.rejectIfEmpty;

@Controller
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    SoigneurService soigneurService;



    @GetMapping (path = "/animaux")
public String AfficherListeAnimaux(Model model){
        List<Animal> listeAnimaux = animalService.getListeAnimaux();
        model.addAttribute("listeAnimaux",listeAnimaux);
        return "listeAnimaux";

    }

    @GetMapping ( path = "/animal")
        public String affichierFormulaireCreation(@ModelAttribute("animal") AnimalDto animalDto, Model model){
        List<Regime> listeRegimes = new ArrayList<>(EnumSet.allOf(Regime.class));
        model.addAttribute("listeRegimes",listeRegimes);
        model.addAttribute("listeEspeces",animalService.getAllEspeces());
        return "formAnimal";
        }

    @PostMapping( path = "/animal")
    public String traiterFormulaireCreation(@Validated @ModelAttribute("animal") AnimalDto animalDto, BindingResult bindingResult, Model model){
        rejectIfEmpty(bindingResult, "regime", "validation");
        if (bindingResult.hasErrors()){
            return affichierFormulaireCreation(animalDto,model);
        }
        Animal animal = animalService.addAnimal(animalDto);
        return "redirect:/animaux";
    }

    @GetMapping(path = "/findAnimal")
    public String RechercheAnimal(@RequestParam(name="filtre") String filtre, Model model){
        List<Animal> listeFiltre = animalService.getListAnimalLike(filtre);
        model.addAttribute("listeAnimaux",listeFiltre);
        return "listeAnimaux";
    }

    @GetMapping("/animal/{idAnimal}")
    public String afficherRecapitulatifAnimal(Model model, @PathVariable Long idAnimal) throws AnimalNonTrouveeExeption {
        var t =animalService.get(idAnimal).getSoigneurs();
        model.addAttribute("DetailAnimal",animalService.get(idAnimal));
        model.addAttribute("soingeursList",t);
        model.addAttribute("soingeursAddList",soigneurService.getListeSoigneur());
        return "DetailAnimal";
    }

    @GetMapping (path = "/delete/{idAnimal}/{idSoigneur}")
    public String deleteSoigneur(Model model,@PathVariable Long idAnimal, @PathVariable Long idSoigneur){
       animalService.deleteSoigneur(idAnimal,idSoigneur);
       return "redirect:/animal/"+idAnimal;
    }

    @GetMapping(path = "/addSoigneurAnimal/{idAnimal}/{idSoigneur}")
    public String addSoigneurAnimal(Model model,@PathVariable Long idAnimal, @PathVariable Long idSoigneur){
        animalService.addSoigneur(idAnimal,idSoigneur);
        return "redirect:/animal/"+idAnimal;
    }




}
