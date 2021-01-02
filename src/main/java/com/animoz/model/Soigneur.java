package com.animoz.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Soigneur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String numero;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateRecrutement;

	@ManyToMany
	@JoinTable(name = "PopulationSoigneur", 
	           joinColumns = @JoinColumn(name = "soigneur_id"), 
	           inverseJoinColumns = @JoinColumn(name = "animal_id"))
	private List<Animal> animaux = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDateRecrutement() {
		return dateRecrutement;
	}

	public void setDateRecrutement(Date dateRecrutement) {
		this.dateRecrutement = dateRecrutement;
	}

	public List<Animal> getAnimaux() {
		return animaux;
	}

	public void setAnimaux(List<Animal> animaux) {
		this.animaux = animaux;
	}
	
	public void addAnimal(Animal animal) {
		if(animal == null) {
			throw new NullPointerException("L'animal ne doit pas être nul");
		}
		if (! this.animaux.contains(animal)) {
			this.animaux.add(animal);
			animal.getSoigneurs().add(this);
		}
	}


}
