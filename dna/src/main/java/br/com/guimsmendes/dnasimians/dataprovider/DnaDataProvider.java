package br.com.guimsmendes.dnasimians.dataprovider;

import br.com.guimsmendes.dnasimians.dataprovider.repository.DnaRepository;
import br.com.guimsmendes.dnasimians.dataprovider.sqs.sender.SqsQueueSender;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import br.com.guimsmendes.dnasimians.usecase.gateway.DnaGateway;
import java.math.BigInteger;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DnaDataProvider implements DnaGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(DnaDataProvider.class);

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
        try {
            return Optional.of(new DnaDomain(((BigInteger) dnaRepository.count(DnaType.SIMIAN.toString())).intValue(),
                    ((BigInteger) dnaRepository.count(DnaType.HUMAN.toString())).intValue()));
        } catch (Exception e) {
            LOGGER.error("Unable to get Stats from Database. Message: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
