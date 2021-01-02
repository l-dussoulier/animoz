package com.animoz.service;

import com.animoz.controller.AnimalDto;
import com.animoz.model.Animal;
import com.animoz.model.Espece;
import com.animoz.model.Soigneur;
import com.animoz.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> getListeAnimaux(){
        return animalRepository.getListAnimaux();
    }

    public List getListAnimalLike(String nom) {
        return animalRepository.getListAnimalLike("%"+nom+"%");
    }

    @Transactional
    public Animal addAnimal(AnimalDto animalDto){
        Animal animal = new Animal();
        animal.setNom(animalDto.getNom());
        animal.setDescription((animalDto.getDescription()));
        animal.setRegime(animalDto.getRegime());
        animal.setEspece((Espece) animalRepository.getEspeceSelect(animalDto.getEspece()));
        animalRepository.save(animal);
        return animal;
    }

    public List<Espece> getAllEspeces() {
        return animalRepository.getAllEspece();
    }

    public Animal get(Long id) throws AnimalNonTrouveeExeption{
        return animalRepository.findById(id);
    }

    public void deleteSoigneur(Long idAnimal, Long idSoigneur) {
        animalRepository.deleteSoigneur(idAnimal, idSoigneur);
    }

    public void addSoigneur(Long idAnimal, Long idSoigneur) {
        animalRepository.addSoigneur(idAnimal,idSoigneur);
    }
}
