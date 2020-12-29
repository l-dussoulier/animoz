package com.animoz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Population {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int nombreIndividus;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNombreIndividus() {
		return nombreIndividus;
	}

	public void setNombreIndividus(int nombreIndividus) {
		this.nombreIndividus = nombreIndividus;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		if(animal == null) {
			throw new NullPointerException("L'animal ne peut pas être nul.");
		}
		if (this.animal != null) {
			this.animal.getPopulations().remove(this);
		}
		this.animal = animal;
		this.animal.getPopulations().add(this);
	}

}
