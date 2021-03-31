package br.com.guimsmendes.dnasimians.usecase;

import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import br.com.guimsmendes.dnasimians.usecase.exception.UseCaseException;
import br.com.guimsmendes.dnasimians.usecase.gateway.DnaGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnaUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DnaUseCase.class);

    private final DnaGateway dnaGateway;
    private final NitrogenBaseUseCase nitrogenBaseUseCase;

    @Autowired
    public DnaUseCase(DnaGateway dnaGateway, NitrogenBaseUseCase nitrogenBaseUseCase) {
        this.dnaGateway = dnaGateway;
        this.nitrogenBaseUseCase = nitrogenBaseUseCase;
    }

    public boolean isSimian(DnaDomain dnaDomain) {
        dnaDomain.setDnaType(nitrogenBaseUseCase.checkDnaType(dnaDomain.getDnaSequence()));
        try {
            dnaGateway.postDnaSequence(dnaDomain);
        }
        catch(Exception e){
            LOGGER.error("Unable to save the DNA into the database. Message: {}", e.getMessage());
        }
        return dnaDomain.getDnaType().equals(DnaType.SIMIAN);
    }

    public DnaDomain getStats() {
        return dnaGateway.getStats().map(dnaDomain -> {
            dnaDomain.setRatio();
            return dnaDomain;
        }).orElseThrow(() -> new UseCaseException.NoRecordsFound("Unable to retrieve human records."));
    }



}
