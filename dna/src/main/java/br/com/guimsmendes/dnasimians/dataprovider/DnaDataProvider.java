package br.com.guimsmendes.dnasimians.dataprovider;

import br.com.guimsmendes.dnasimians.dataprovider.repository.DnaRepository;
import br.com.guimsmendes.dnasimians.dataprovider.sqs.sender.SqsQueueSender;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import br.com.guimsmendes.dnasimians.usecase.gateway.DnaGateway;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DnaDataProvider implements DnaGateway {

    private static final int ZERO = 0;

    private final SqsQueueSender sqsQueueSender;
    private final DnaRepository dnaRepository;

    @Autowired
    public DnaDataProvider(SqsQueueSender sqsQueueSender, DnaRepository dnaRepository) {
        this.sqsQueueSender = sqsQueueSender;
        this.dnaRepository = dnaRepository;
    }

    @Override
    public void postDnaSequence(DnaDomain dnaDomain) {
        sqsQueueSender.send(dnaDomain);
    }

    @Override
    public Optional<DnaDomain> getStats() {
        return Optional.of(new DnaDomain(dnaRepository.count(DnaType.SIMIAN).orElse(ZERO), dnaRepository.count(DnaType.HUMAN).orElse(ZERO)));
    }
}
