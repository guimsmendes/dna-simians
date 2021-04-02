package br.com.guimsmendes.dnasimians.dataprovider;

import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import br.com.guimsmendes.dnasimians.dataprovider.repository.DnaRepository;
import br.com.guimsmendes.dnasimians.dataprovider.sqs.interceptor.SqsQueueInterceptor;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import br.com.guimsmendes.dnasimians.usecase.gateway.DnaGateway;

import static br.com.guimsmendes.dnasimians.dataprovider.constant.Constants.DNA_TABLE_NAME;
import static br.com.guimsmendes.dnasimians.dataprovider.constant.Constants.POSTGRES_DATABASE_NAME;

import java.math.BigInteger;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DnaDataProvider implements DnaGateway {

	private static final Logger LOGGER = LoggerFactory.getLogger(DnaDataProvider.class);

	private final SqsQueueInterceptor sqsQueueInterceptor;
	private final DnaRepository dnaRepository;

	@Autowired
	public DnaDataProvider(SqsQueueInterceptor sqsQueueInterceptor, DnaRepository dnaRepository) {
		this.sqsQueueInterceptor = sqsQueueInterceptor;
		this.dnaRepository = dnaRepository;
	}

	@Override
	public void postDnaSequence(DnaDomain dnaDomain) {
		String dnaId = sqsQueueInterceptor.send(dnaDomain).orElseThrow(
				() -> new DataProviderException.UnableToSaveRecord("Unable to save record for DNA on Table dna."));
		LOGGER.info(
				"The DNA record from Type: {} and Id: {} has been successfully added to the database: {}, table {}.",
				dnaDomain.getDnaType(), dnaId, POSTGRES_DATABASE_NAME, DNA_TABLE_NAME);
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
