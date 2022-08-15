package br.com.estacionalegal.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.estacionalegal.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest {
    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("state[0]", Matchers.equalTo("SE"));
    }

    @Test
    void whenCreateThenCheckIsCreated() {
        var parking = new ParkingCreateDTO();
        parking.setColor("verde");
        parking.setModel("civic");
        parking.setLicense("tre-3200");
        parking.setState("al");

        RestAssured.given()
                .when()
                .body(parking)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
