package br.com.guimsmendes.dnasimians.usecase.gateway;

import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface DnaGateway {

    void postDnaSequence(DnaDomain dnaDomain);
    Optional<DnaDomain> getStats();
}
