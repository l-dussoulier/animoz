package com.animoz.service;

import com.animoz.controller.SoigneurDto;
import com.animoz.model.Soigneur;
import com.animoz.repository.SoigneurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SoigneurService {

    @Autowired
    private SoigneurRepository soigneurRepository;

    public List<Soigneur> getListeSoigneur(){
        System.out.println(soigneurRepository.getListSoigneur());
        return soigneurRepository.getListSoigneur();
    }

    @Transactional
    public Soigneur addSoigneur(SoigneurDto soigneurDto){


        Soigneur soigneur = new Soigneur();
        soigneur.setNom(soigneurDto.getNom());
        soigneur.setNumero(soigneurDto.getNumero());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse((CharSequence) soigneurDto.getDateRecrutement(), formatter);
        if (localDate instanceof LocalDate){
            System.out.println(localDate);
            soigneur.setDateRecrutement(localDate);
        }
        System.out.println("probleme frere");
        soigneurRepository.save(soigneur);


        return soigneur;
    }

}


