package br.com.udemy.domain;

import br.com.udemy.common.PlanetSingleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

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

}