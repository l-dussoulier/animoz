package com.animoz.repository;

import com.animoz.model.Animal;
import com.animoz.model.Espece;
import com.animoz.model.PopulationSoigneur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Long.parseLong;

@Repository
public class AnimalRepository {

    @Autowired
    private EntityManager em;

    public Animal findById(Long id) {
        return (Animal) em.createQuery("select a from Animal a where a.id = :param")
                .setParameter("param", id)
                .getSingleResult();
    }


    public List<Animal> getListAnimaux() {
        return em.createQuery("select a from Animal a ORDER BY a.nom", Animal.class)
                .getResultList();
    }

    public List<Animal> getListAnimalLike(String nom) {
        List request = em.createQuery("select a from Animal a where a.nom like :param ")
                .setParameter("param", nom)
                .getResultList();
        return request;
    }

    public void save(Animal a) {
        try {
            Animal animal = new Animal();
            animal.setNom(a.getNom());
            animal.setDescription(a.getDescription());
            animal.setRegime(a.getRegime());
            animal.setEspece(a.getEspece());
            em.persist(animal);
        } catch (Error e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public List<Espece> getAllEspece() {
        return em.createQuery("select a from Espece a ").getResultList();
    }

    public Espece getEspeceSelect(String nom) {
        return (Espece) em.createQuery("select a from Espece a where a.nom = :param")
                .setParameter("param", nom)
                .getSingleResult();
    }


    @Transactional
    public void deleteSoigneur(Long idAnimal, Long idSoigneur) {
        // ne Fonctionne pas encore pour vérifier si il existe au moins 1 soigneur pour l'animal
       // Query q0 = em.createNativeQuery("select count(*) from PopulationSoigneur where animal_id = ?1");
       // q0.setParameter(1,idAnimal);
       // var t =  q0.getResultList();
        Query query = em.createNativeQuery("DELETE FROM PopulationSoigneur WHERE animal_id = ?1 AND soigneur_id = ?2");
        query.setParameter(1, idAnimal);
        query.setParameter(2, idSoigneur);
        query.executeUpdate();
    }

    @Transactional
    public void addSoigneur(Long idAnimal, Long idSoigneur) {
        Query query = em.createNativeQuery("INSERT INTO PopulationSoigneur VALUES (?1,?2)");
        query.setParameter(1, idAnimal);
        query.setParameter(2, idSoigneur);
        query.executeUpdate();
    }
}

