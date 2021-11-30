// 1 - Pacote
package petstore;

//2 - Bibliotecas

import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

//3- Classe
public class Pet {
    //3.1 - Atributos
    String url = "https://petstore.swagger.io/v2/pet"; //Endereço da entidade pet

   //3.2 Metodos (ação que não retorna nada) e funções (Retorna um resultado)
   public String lerJson(String caminhoJson) throws IOException {
       return  new String(Files.readAllBytes(Paths.get(caminhoJson)));
   }
   // incluir - create - post
    @Test
   public void incluirPet() throws IOException {
       String jsonBody = lerJson("db/pet1.json");

       //Sintaxe gherkin
        //Dado - Quando - Então - isso em pt
        // Given - When - Then - em Ingles

        given()
                .contentType("application/json")//Comum em API REST
                .log().all()
                .body(jsonBody)
        .when()
                .post(url)
        .then()
                .log().all()
                .statusCode(200)
                .body("name",  is("Guilherme"))
                .body("status", is("available"))
        ;

   }

}
