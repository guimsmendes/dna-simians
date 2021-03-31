package br.com.guimsmendes.dnasimians.dataprovider.sqs.listener;

import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import br.com.guimsmendes.dnasimians.dataprovider.mapper.DnaMapper;
import br.com.guimsmendes.dnasimians.dataprovider.repository.DnaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;

import java.util.Map;
import java.util.UUID;

import static br.com.guimsmendes.dnasimians.dataprovider.constant.Constants.*;

@Component
public class SQSQueueListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQSQueueListener.class);

    private final DnaRepository dnaRepository;
    private final DnaMapper dnaMapper;

    @Autowired
    public SQSQueueListener(DnaRepository dnaRepository, DnaMapper dnaMapper) {
        this.dnaRepository = dnaRepository;
        this.dnaMapper = dnaMapper;
    }

    @SqsListener(value = "${cloud.aws.sqs.endpoint}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void dnaSequenceListener(DnaDomain dnaDomain, Map<String, Object> headers) {
        UUID correlationId = (UUID) headers.get(CORRELATION_ID);
        MDC.put("correlation.id", String.valueOf(correlationId));
        LOGGER.info("SQS CONSUMER INTERCEPTOR = TOPIC: {} CORRELATION.ID: {}", "${cloud.aws.sqs.endpoint}", correlationId);

        Object response = dnaRepository.postDnaSequence(dnaMapper.toDnaEntity(dnaDomain)).toString();
        if (!(response == "")) {
            LOGGER.info("The DNA record from Type: {} and Id: {} has been successfully added to the database: {}, table {}.", dnaDomain.getDnaType(), response, POSTGRES_DATABASE_NAME, DNA_TABLE_NAME);
        } else {
            throw new DataProviderException
                    .UnableToSaveRecord("Unable to save record for DNA of Correlation Id {} on Table dna.", String.valueOf(correlationId));
        }
    }
}

