package com.animoz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
//@NamedQuery(name="deleteSoigneur", query="delete from Animal.soigneurs a where a.id = :id")
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String origine;
	private String description;
	@Enumerated(STRING)
	@Column(nullable = false)
	private Regime regime;

	@ManyToOne
	@JoinColumn(name = "espece_id")
	private Espece espece;

	@ManyToMany(mappedBy = "animaux")
	private List<Soigneur> soigneurs = new ArrayList<>();

	@OneToMany(mappedBy = "animal")
	private List<Population> populations = new ArrayList<>();

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

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
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

	public Espece getEspece() {
		return espece;
	}

	public void setEspece(Espece espece) {
		this.espece = espece;
	}

	public List<Soigneur> getSoigneurs() {
		return soigneurs;
	}

	public void setSoigneurs(List<Soigneur> soigneurs) {
		this.soigneurs = soigneurs;
	}

	public List<Population> getPopulations() {
		return populations;
	}

	public void setPopulations(List<Population> populations) {
		this.populations = populations;
	}

}
