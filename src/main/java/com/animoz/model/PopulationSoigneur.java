package com.animoz.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class PopulationSoigneur {

	@Id

	private Long animal_id;
	private Long soigneur_id;

	public Long getAnimal_id() {
		return animal_id;
	}

	public void setAnimal_id(Long animal_id) {
		this.animal_id = animal_id;
	}

	public Long getSoigneur_id() {
		return soigneur_id;
	}

	public void setSoigneur_id(Long soigneur_id) {
		this.soigneur_id = soigneur_id;
	}
}
