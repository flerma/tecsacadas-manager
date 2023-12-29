# tecsacadas-manager
Sistema de genciamento financeiro e backoffice da empresa TecSacadas

Tecnolog�as Utilizadas.

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8.8](https://maven.apache.org)
- Springboot 2.7.18


### Run docker compose up inside docker folder to start camunda postgres database
```shell
docker-compose up -d
```

### Compilaci�n
```shell
mvn -DskipTests=true clean compile
```

### Construcci�n
```shell
mvn -DskipTests=true clean package
```

### Ejecuci�n ambiente local
```shell
mvn spring-boot:run -D spring-boot.run.profiles=local
```

### Unit Test
```shell
mvn clean test
```

se pueden volver a correr los tests
### Swagger

http://localhost:8081/swagger-ui.html


### Running the application locally

```shell
mvn spring-boot:run
```

### Deploy on server
- Executar o comando abaixo
```shell
mvn -DskipTests=true clean package
```
 - Entrar no site abaixo e fazer o login
   * https://server35.integrator.com.br:2083/
   * Usu�rio: ferna3326
   * Senha: Fml418512$

 - Entrar no item **Integrator Spring Boot**
 - Clicar na aba deploy
 - Fazer o upload do arquivo .jar gerardo na pasta target
 - Fazer as devidas configura��es
   * Dom�nio: ferna3326.c35.integrator.host
   * Contexto: /
   * Porta: 3803
   * JDK 17
   * Mem�ria: 256