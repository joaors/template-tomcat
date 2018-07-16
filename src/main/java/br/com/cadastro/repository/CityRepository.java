/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastro.repository;

import br.com.cadastro.model.City;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Aluno
 */
public class CityRepository {
    
    @Inject
    private EntityManager em;
    
    public List<City> list() {
        return em.createQuery("select c from City c", City.class)
                    .getResultList();
    }
    
    public void create(String name, int population) {
        em.getTransaction().begin();
        try {
            City city = new City(name, population);
            em.persist(city);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public City findOne(Long id) {
        try {
            return em.find(City.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<City> update(Long id, String name, int population) {
            em.getTransaction().begin();
            Optional<City> found = Optional.ofNullable(em.find(City.class, id));
            found.ifPresent(t -> {
                    t.setName(name);
                    t.setPopulation(population);
            });
            em.getTransaction().commit();        
            return found;
    }

    public void remove(long id) {
        em.getTransaction().begin();
        Optional<City> found = Optional.ofNullable(findOne(id));
        found.ifPresent(t -> em.remove(t));
        em.getTransaction().commit();
    }
    
}
