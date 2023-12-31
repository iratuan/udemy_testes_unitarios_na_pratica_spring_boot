package br.com.udemy.web;

import br.com.udemy.domain.Planet;
import br.com.udemy.domain.PlanetService;
import br.com.udemy.domain.QueryBuilderPlanet;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService service;

    @GetMapping
    public ResponseEntity<List<Planet>> listAllPlanets(Planet planet) {
        var example = QueryBuilderPlanet.build(planet);
        var planets = service.listAll(example);
        if (planets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(planets);
    }

    @PostMapping
    public ResponseEntity<Planet> createPlannet(@RequestBody Planet planet) {
        var planetCreated = service.create(planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(planetCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable("id") Long id) {
        return service.get(id).map(planet -> ResponseEntity.ok(planet))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlanetById(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return  ResponseEntity.notFound().build();
        }
    }
}
