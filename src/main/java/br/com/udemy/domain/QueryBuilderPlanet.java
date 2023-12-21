package br.com.udemy.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class QueryBuilderPlanet {
    public static Example build(Planet planet){
        var matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(planet, matcher);

        return example;
    }
}
