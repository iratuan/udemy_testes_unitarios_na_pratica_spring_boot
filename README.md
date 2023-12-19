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
