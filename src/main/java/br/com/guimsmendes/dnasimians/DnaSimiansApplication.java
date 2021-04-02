package br.com.guimsmendes.dnasimians;

import br.com.guimsmendes.dnasimians.dataprovider.repository.DnaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = DnaRepository.class)
@SpringBootApplication
public class DnaSimiansApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnaSimiansApplication.class, args);
    }

}
