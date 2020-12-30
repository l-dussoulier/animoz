package com.animoz.controller;

import com.animoz.model.Espece;
import com.animoz.model.Regime;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import static javax.persistence.EnumType.STRING;

public class AnimalDto {
    private String nom;
    private String description;
    private Regime regime;
    private String espece;

    @NotBlank(message = "Le nom de l''animal est obligatoire")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }
}
