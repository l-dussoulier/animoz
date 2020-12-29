package com.animoz.repository;

import com.animoz.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AnimalRepository {

    @Autowired
    private EntityManager entityManager;


    public List<Animal> getListAnimaux() {
        return entityManager.createQuery("select a from Animal a ORDER BY a.nom", Animal.class)
                            .getResultList();
    }

    public List<Animal> getListAnimalLike(String nom) {
        List request = entityManager.createQuery("select a from Animal a where a.nom like :param ")
                                    .setParameter("param",nom)
                                    .getResultList();

        return request;
    }

}
