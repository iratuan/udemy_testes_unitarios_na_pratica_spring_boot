package br.com.udemy.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository repository;

    public List<Planet> listAll(Example<Planet> example){
        return repository.findAll(example);
    }
    public Planet create(Planet planet) {
        return  repository.save(planet);
    }

    public Optional<Planet> get(Long id) {
        return repository.findById(id);
    }
}
