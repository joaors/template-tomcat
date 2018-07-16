/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastro.service;

import br.com.cadastro.model.City;
import br.com.cadastro.repository.CityRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.inject.Inject;

/**
 *
 * @author Aluno
 */
public class CityService {
    
    @Inject
    private CityRepository cityRepository;
    
    
    public List<City> getCities() {
        return cityRepository.list();
    }
    
    public void createCity(String name, int population) {
        cityRepository.create(name, population);
    }
    
    public City getCity(Long id) {
        return cityRepository.findOne(id);
    }

    public Optional<City> updateCity(long id, String name, int population) {
        return cityRepository.update(id, name, population);
        
    }

    public void removeCity(long id) {
        cityRepository.remove(id);
    }
}
