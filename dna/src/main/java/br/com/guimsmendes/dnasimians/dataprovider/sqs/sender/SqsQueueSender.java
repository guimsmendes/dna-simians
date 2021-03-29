package br.com.guimsmendes.dnasimians.dataprovider.sqs.sender;

import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SqsQueueSender {
	
	private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.sqs.endpoint}")
    private String sqsEndPoint;

    @Autowired
    public SqsQueueSender(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public void send(DnaDomain dnaDomain) {
        queueMessagingTemplate.convertAndSend(sqsEndPoint, dnaDomain);
    }

}
