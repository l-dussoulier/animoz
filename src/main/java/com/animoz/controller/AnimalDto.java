package com.animoz.controller;

import javax.validation.constraints.NotBlank;

public class AnimalDto {
    private String nom;

    @NotBlank(message = "Le nom de l''animal est obligatoire")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
