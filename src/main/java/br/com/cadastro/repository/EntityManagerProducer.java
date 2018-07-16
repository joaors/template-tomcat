/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastro.repository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Aluno
 */
public class EntityManagerProducer {
    
    private final static EntityManagerFactory factory = Persistence.createEntityManagerFactory("senai");    
      
    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return factory.createEntityManager();
    }
    
    public void closeEntityManager(@Disposes EntityManager manager) {
        manager.close();
    }
}
