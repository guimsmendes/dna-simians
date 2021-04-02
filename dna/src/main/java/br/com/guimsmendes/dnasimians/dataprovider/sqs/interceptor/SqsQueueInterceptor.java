package br.com.guimsmendes.dnasimians.dataprovider.sqs.interceptor;

import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import br.com.guimsmendes.dnasimians.dataprovider.mapper.DnaMapper;
import br.com.guimsmendes.dnasimians.dataprovider.repository.DnaRepository;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SqsQueueInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SqsQueueInterceptor.class);

	private final QueueMessagingTemplate queueMessagingTemplate;
	private final DnaRepository dnaRepository;
	private final DnaMapper dnaMapper;

	@Value("${cloud.aws.sqs.endpoint}")
	private String sqsEndPoint;

	@Autowired
	public SqsQueueInterceptor(QueueMessagingTemplate queueMessagingTemplate, DnaRepository dnaRepository,
			DnaMapper dnaMapper) {
		this.queueMessagingTemplate = queueMessagingTemplate;
		this.dnaRepository = dnaRepository;
		this.dnaMapper = dnaMapper;
	}

	public Optional<String> send(DnaDomain dnaDomain) {
		try {
			queueMessagingTemplate.convertAndSend(sqsEndPoint, dnaDomain);
			LOGGER.info("SQS PRODUCER INTERCEPTOR = TOPIC: {}", sqsEndPoint);
		} catch (Exception e) {
			throw new DataProviderException.SqsSenderException("Failed to connect to SQS topic: {}. Message: {}",
					sqsEndPoint, e.getMessage());
		}
		return this.receive();
	}

	private Optional<String> receive() {

		DnaDomain dnaDomain = this.queueMessagingTemplate.receiveAndConvert(sqsEndPoint, DnaDomain.class);
		LOGGER.info("SQS CONSUMER INTERCEPTOR = TOPIC: {}", sqsEndPoint);

		String response = dnaRepository.postDnaSequence(dnaMapper.toDnaEntity(dnaDomain)).toString();
		return !response.equals("") ? Optional.of(response) : Optional.empty();

	}

}
