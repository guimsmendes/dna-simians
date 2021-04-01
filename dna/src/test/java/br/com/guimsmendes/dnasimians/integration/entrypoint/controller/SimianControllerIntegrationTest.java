package br.com.guimsmendes.dnasimians.integration.entrypoint.controller;

import br.com.guimsmendes.dnasimians.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.blankOrNullString;

public class SimianControllerIntegrationTest extends IntegrationTest {

    @Test
    void registerValidHumanDnaType(){
        String payload = "{\"dna\":[\"ATG\",\"CGA\",\"ATG\"]}";
        given()
                .contentType(JSON)
                .body(payload)
                .post("/simian")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .body(blankOrNullString());
    }
}
