package br.com.guimsmendes.dnasimians.integration.entrypoint.controller;

import br.com.guimsmendes.dnasimians.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.blankOrNullString;

class SimianControllerIntegrationTest extends IntegrationTest {

	@Test
	void registerValidHumanDnaType1x1() {
		simianTestCase("{\"dna\":[\"A\"]}", HttpStatus.FORBIDDEN);
	}

	@Test
	void registerValidHumanDnaType3x3() {
		simianTestCase("{\"dna\":[\"ATG\",\"CGA\",\"ATG\"]}", HttpStatus.FORBIDDEN);
	}

	@Test
	void registerValidHumanDnaType5x5() {
		simianTestCase("{\"dna\":[\"ATGAC\",\"CGATA\",\"ATGAG\",\"ATGAG\", \"CGATA\"]}", HttpStatus.FORBIDDEN);
	}

	@Test
	void registerValidSimianDnaType5x5Horizontal() {
		simianTestCase("{\"dna\":[\"aaaAC\",\"CGATA\",\"ATGAG\",\"ATGAG\", \"CGATA\"]}", HttpStatus.OK);
	}
	
	@Test
	void registerValidSimianDnaType5x5HorizontalBlankSpace() {
		simianTestCase("{\"dna\":[\"aa a  A C\",\" C  G ATA\",\"AT GA G \",\"A   T GAG\", \" C G ATA\"]}", HttpStatus.OK);
	}

	@Test
	void registerValidSimianDnaType5x5Vertical() {
		simianTestCase("{\"dna\":[\"ATGAC\",\"aGATA\",\"aTGAG\",\"aTGAG\", \"CGATA\"]}", HttpStatus.OK);
	}

	@Test
	void registerValidSimianDnaType5x5DiagonalUpper() {
		simianTestCase("{\"dna\":[\"ATGAC\",\"CAATA\",\"ATAAG\",\"ATGAG\", \"CGATA\"]}", HttpStatus.OK);
	}

	@Test
	void registerValidSimianDnaType5x5DiagonalDowner() {
		simianTestCase("{\"dna\":[\"ATGAC\",\"CGACA\",\"ATCAG\",\"ACGAG\", \"CGATA\"]}", HttpStatus.OK);
	}

	@Test
	void registerInvalidNumberOfRows() {
		simianTestCase("{\"dna\":[\"ATGAC\",\"CGATA\",\"ATGAG\",\"ATGAG\", \"CGATA\"], \"CGATA\"]}",
				HttpStatus.BAD_REQUEST);
	}

	@Test
	void registerInvalidNumberOfColumns() {
		simianTestCase("{\"dna\":[\"ATGAAC\",\"CGATA\",\"ATGAG\",\"ATGAG\", \"CGATA\"]}", HttpStatus.BAD_REQUEST);
	}

	@Test
	void registerInvalidBaseTypes() {
		simianTestCase("{\"dna\":[\"AFSDC\",\"CGATA\",\"ATGAG\",\"ATGAG\", \"CGATA\"]}", HttpStatus.BAD_REQUEST);
	}

	@Test
	void getStats() {
		given().contentType(JSON).get("/stats").then().statusCode(HttpStatus.OK.value());
	}

	private void simianTestCase(String payload, HttpStatus http) {
		given().contentType(JSON).body(payload).post("/simian").then().statusCode(http.value())
				.body(blankOrNullString());

	}

}
