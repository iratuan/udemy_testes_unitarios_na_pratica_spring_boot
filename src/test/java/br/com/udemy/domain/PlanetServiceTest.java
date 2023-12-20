package br.com.udemy.domain;

import br.com.udemy.common.PlanetSingleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/*
 * Regra para nomeação dos métodos
 * operacao_estado_retorno
 */
@ExtendWith(MockitoExtension.class)
class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;

    /*
    * Exemplo de teste do tipo AAA onde
    * */
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {
        Planet planet = PlanetSingleton.getInstance();

        // Mockando o comportamento
        // Arrange -> Primeiro A de AAA
        when(planetRepository.save(planet)).thenReturn(planet);

        // Act -> Segundo A de AAA
        // system under test (geralmente, o retorno esperado)
        Planet sut = planetService.create(planet);

        // Assert -> Terceiro A de AAA
        assertThat(sut).isEqualTo(planet);
    }

    @Test
    public void createPlanet_WithInvalideData_ThrowsException(){
        Planet invalidPlanet = PlanetSingleton.getInvalidInstance();
        when(planetRepository.save(invalidPlanet)).thenThrow(DataValidationException.class);
        assertThatThrownBy(() -> planetService.create(invalidPlanet)).isInstanceOf(DataValidationException.class);
    }

    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet(){
        // Recupera uma instância válida de planet
        Planet planet = PlanetSingleton.getInstance();
        // Cenário que mocka o comportamento
        when(planetRepository.findById(any())).thenReturn(Optional.ofNullable(planet));
        // SUT
        Optional<Planet> sut = planetService.get(1L);
        // Verificações
        // Note que verificamos se o optional retorna uma instância
        assertThat(sut).isNotEmpty();
        // Verificamos se a instância retornada, de fato, coincide com o esperado.
        assertThat(sut.get()).isEqualTo(planet);
    }

    @Test
    public void getPlanet_ByUnexistingId_ReturnsEmpty(){
        // Cenário que mocka o comportamento
        when(planetRepository.findById(any())).thenReturn(Optional.empty());
        // SUT
        Optional<Planet> sut = planetService.get(1L);
        // Verificações
        assertThat(sut).isEmpty();
    }

    @Test
    public void getPlanets_ByExistingName_ReturnsListOfPlanets(){

        //Preparando o cenário
        var planet = PlanetSingleton.getInstance();
        planet.setName("Saturn");
        var matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(planet, matcher);
        var listOfPlanets = List.of(planet);
        // Cenário que mocka o comportamento
        when(planetRepository.findAll(example)).thenReturn(listOfPlanets);

        //SUT
        var sut = planetService.listAll(example);
        // Verificações
        assertThat(sut).isEqualTo(listOfPlanets);
    }

    @Test
    public void getPlanets_ByUnexistingName_ReturnsEmptyList(){

        //Preparando o cenário
        var planet = PlanetSingleton.getInstance();
        planet.setName("Unexisting Name");
        var matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(planet, matcher);
        List<Planet> emptyList = Collections.emptyList();
        // Cenário que mocka o comportamento
        when(planetRepository.findAll(example)).thenReturn(emptyList);

        //SUT
        var sut = planetService.listAll(example);
        // Verificações
        assertThat(sut).isEmpty();
    }

}