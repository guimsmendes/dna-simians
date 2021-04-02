package br.com.guimsmendes.dnasimians.acceptance.steps;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import io.cucumber.spring.CucumberContextConfiguration;

import br.com.guimsmendes.dnasimians.entrypoint.controller.SimianController;
import br.com.guimsmendes.dnasimians.entrypoint.model.request.SimianRequest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimianSteps implements io.cucumber.java8.En {
	
	@Autowired
	private SimianController simianController;
	private SimianRequest simianRequest;
	private HttpStatus response;
	
	public SimianSteps() {
		Given("a dna sequence {string}", (String dna) -> {
			String[] dnaSequence = dna.split(",");
			this.simianRequest = new SimianRequest(dnaSequence);
			
		});
		When("is checked its dna type", ()-> {
			this.response = this.simianController.isSimian(this.simianRequest).getStatusCode();
		});
		
		Then("return as Human Type", ()->{
			assertEquals(HttpStatus.FORBIDDEN, this.response);
		});
		
		Then("return as Simian Type", ()->{
			assertEquals( HttpStatus.OK, this.response);
		});
	}

}
