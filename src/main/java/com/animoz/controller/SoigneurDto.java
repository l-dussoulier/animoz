package com.animoz.controller;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

public class SoigneurDto {
    private String nom;
    private String numero;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La date est obligatoire")
    @PastOrPresent(message = "La date ne peut être dans le futur")
    private Date dateRecrutement;

    @NotBlank(message = "Le nom du soigneur est obligatoire")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @NotBlank(message = "le numéro est obligatoire")
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    //@NotBlank(message = "La date de recrutement est obligatoire")
    public Date getDateRecrutement(){ return dateRecrutement; }

    public void setDateRecrutement(Date dateRecrutement) {
        System.out.println("bien");
        this.dateRecrutement = dateRecrutement;
    }

}
