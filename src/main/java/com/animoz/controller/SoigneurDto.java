package com.animoz.controller;

import javax.validation.constraints.NotBlank;

public class SoigneurDto {
    private String nom;
    private String numero;

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
}
