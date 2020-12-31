package com.animoz.repository;

import com.animoz.model.Animal;
import com.animoz.model.Espece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AnimalRepository {

    @Autowired
    private EntityManager em;


    public List<Animal> getListAnimaux() {
        return em.createQuery("select a from Animal a ORDER BY a.nom", Animal.class)
                            .getResultList();
    }

    public List<Animal> getListAnimalLike(String nom) {
        List request = em.createQuery("select a from Animal a where a.nom like :param ")
                                    .setParameter("param",nom)
                                    .getResultList();

        return request;
    }

    public void save(Animal a){
        try {
            Animal animal = new Animal();
            animal.setNom(a.getNom());
            animal.setDescription(a.getDescription());
            animal.setRegime(a.getRegime());
            animal.setEspece(a.getEspece());
            em.persist(animal);
        }
        catch (Error e){
            System.out.println(e);
        }
        finally {
            em.close();
        }
    }

    public List<Espece> getAllEspece() {
    return em.createQuery("select a from Espece a ").getResultList();
    }

    public Espece getEspeceSelect(String nom){
        return (Espece) em.createQuery("select a from Espece a where a.nom = :param")
                .setParameter("param",nom)
                .getSingleResult();
    }



}
