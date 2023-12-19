package br.com.udemy.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository repository;
    public Planet create(Planet planet) {
        return  repository.save(planet);
    }
}
