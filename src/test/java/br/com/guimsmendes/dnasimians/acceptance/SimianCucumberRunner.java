package br.com.guimsmendes.dnasimians.acceptance;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) 
@CucumberOptions(plugin = {"pretty"}, features = "classpath:features", tags = "@simian", publish = true)
public class SimianCucumberRunner {

} 
