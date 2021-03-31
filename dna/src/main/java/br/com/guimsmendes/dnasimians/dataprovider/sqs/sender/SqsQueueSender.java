package br.com.guimsmendes.dnasimians.dataprovider.sqs.sender;

import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static br.com.guimsmendes.dnasimians.dataprovider.constant.Constants.CORRELATION_ID;

@Component
public class SqsQueueSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqsQueueSender.class);

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.sqs.endpoint}")
    private String sqsEndPoint;

    @Autowired
    public SqsQueueSender(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public void send(DnaDomain dnaDomain) {
        try {
            UUID correlationId = UUID.randomUUID();
            Map<String, Object> headers = new HashMap<>();
            headers.put(CORRELATION_ID, correlationId);
            queueMessagingTemplate.convertAndSend(sqsEndPoint, dnaDomain, headers);
            LOGGER.info("SQS PRODUCER INTERCEPTOR = TOPIC: {} CORRELATION.ID: {}", sqsEndPoint, correlationId);
        } catch (Exception e) {
            throw new DataProviderException.SqsSenderException("Failed to connect to SQS topic: {}. Message: {}", sqsEndPoint, e.getMessage());
        }
    }

}
