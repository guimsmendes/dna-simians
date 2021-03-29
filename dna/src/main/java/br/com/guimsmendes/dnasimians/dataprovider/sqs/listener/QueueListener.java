package br.com.guimsmendes.dnasimians.dataprovider.sqs.listener;

import br.com.guimsmendes.dnasimians.dataprovider.mapper.DnaMapper;
import br.com.guimsmendes.dnasimians.dataprovider.repository.DnaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;

@Component
public class QueueListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(QueueListener.class);

	private final DnaRepository dnaRepository;
	private final DnaMapper dnaMapper;

	@Autowired
	public QueueListener(DnaRepository dnaRepository, DnaMapper dnaMapper) {
		this.dnaRepository = dnaRepository;
		this.dnaMapper = dnaMapper;
	}

	@SqsListener(value = "${cloud.aws.sqs.endpoint}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void dnaSequenceListener(DnaDomain dnaDomain) {
		dnaRepository.save(dnaMapper.toDnaEntity(dnaDomain));
		
	}

}
