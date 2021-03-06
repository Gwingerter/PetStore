// 1 - Pacote
package petstore;

//2 - Bibliotecas

import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

//3- Classe
public class Pet {
    //3.1 - Atributos
    String url = "https://petstore.swagger.io/v2/pet"; //Endere?o da entidade pet

   //3.2 Metodos (a??o que n?o retorna nada) e fun??es (Retorna um resultado)
   public String lerJson(String caminhoJson) throws IOException {
       return  new String(Files.readAllBytes(Paths.get(caminhoJson)));
   }
   // incluir - create - post
    @Test
   public void incluirPet() throws IOException {
       String jsonBody = lerJson("db/pet1.json");

       //Sintaxe gherkin
        //Dado - Quando - Ent?o - isso em pt
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
                .body("category.name", is("AXNOME"))
                .body("tags.name", contains("sta"))

        ;

   }
    @Test
   public void consultarPet(){
       String petId = "19970310112";

       String   Token =

      given()
              .contentType("application/json")
              .log().all()
      .when()
              .get(url + "/" + petId)
      .then()
              .log().all()
              .statusCode(200)
              .body("category.name",is("AXNOME"))
              .body("tags.id", contains(2021))
      .extract()
              .path("Category.name")
               ;
        System.out.println("O token ? " + Token);
       }

}
