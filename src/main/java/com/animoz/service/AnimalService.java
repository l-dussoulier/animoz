package com.animoz.service;

import com.animoz.model.Animal;
import com.animoz.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
