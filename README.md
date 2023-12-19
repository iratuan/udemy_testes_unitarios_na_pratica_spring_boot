# Repositório de código do laboratório de testes na prática utilizando junit e spring boot.
### Aluno: Francisco Iratuã Nobre Júnior

* url do curso: (https://www.udemy.com/course/testes-automatizados-na-pratica-com-spring-boot)
* data de início: 19/12/2023

### Criação do projeto

Crie um projeto utilizando o spring initializer (https://start.spring.io)
Dependências:
* spring-boot-starter-web
* spring-boot-starter-data-jpa
* postgresql
* spring-boot-starter-test

Faça o download do SGDB Postgres e instale em sua máquina. Você pode utilizar um container docker como opção.
Crie uma base de dados e configure o arquivo resources/applicattion.properties da seguinte forma
```
# Informações da conexão com a base de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/udemy_lab_testes
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
 *não esqueça de configurar corretamente o nome da base, usuário e senha de acesso ao banco de dados*
 
__________
## Criando as classes iniciais
Os pacotes são indicados na primeira linha de código de cada classe.
Atente que, criamos uma classe auxiliar para efetuar os testes, chamada 'PlanetConstants';
A razão da sua existência é unicamente auxiliar os testes, instanciando um objeto com os dados controlados.

*Planet.java*

```
package br.com.udemy.domain;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "planets")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String climate;
    private String terrain;

    public Planet() {

    }

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Override
    public boolean equals(Object obj){
        return EqualsBuilder.reflectionEquals(obj, this);
    }
}
```
*PlanetRepository.java*
````
package br.com.udemy.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
}

````

*PlanetService.java*
````
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

````

Contexto de testes.

*PlanetServiceTest.java*
````
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
````

*PlanetConstants.java *
````
package br.com.udemy.common;

import br.com.udemy.domain.Planet;

public class PlanetConstants {
    public static  final Planet PLANET = new Planet("name","climate","terrain");
}

````

*Refactory para PlanetSingleton.java*
Fizemos essa alteração para introduzir boas práticas e padrões de projetos. Sinta-se livre para escolher a melhor abordagem.
_O Singleton é um padrão de projeto criacional, que garante que apenas um objeto desse tipo exista e forneça um único ponto de acesso a ele para qualquer outro código._
````
package br.com.udemy.common;

import br.com.udemy.domain.Planet;

public class PlanetSingleton {
    private static Planet instance;
    public static Planet getInstance(){
        if (instance == null){
            instance =  new Planet("name","climate","terrain");
        }
        return instance;
    }
}

````