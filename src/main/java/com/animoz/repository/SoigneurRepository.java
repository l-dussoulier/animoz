package com.animoz.repository;

import com.animoz.model.Soigneur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Repository
public class SoigneurRepository {

    @Autowired
    private EntityManager em;


    public List<Soigneur> getListSoigneur() {
        return em.createQuery("select s from Soigneur s ORDER BY s.nom", Soigneur.class)
                            .getResultList();
    }

    public void save(Soigneur s){
        try {
            Soigneur soigneur = new Soigneur();
            soigneur.setNom(s.getNom());
            soigneur.setNumero(s.getNumero());
            System.out.println(s.getDateRecrutement());
            soigneur.setDateRecrutement(s.getDateRecrutement());
            em.persist(soigneur);

        }
        catch (Error e){
            System.out.println(e);
        }
        finally {
            em.close();

        }



    }

}
