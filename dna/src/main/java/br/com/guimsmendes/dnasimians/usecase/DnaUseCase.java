package br.com.guimsmendes.dnasimians.usecase;

import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import br.com.guimsmendes.dnasimians.usecase.exception.UseCaseException;
import br.com.guimsmendes.dnasimians.usecase.gateway.DnaGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnaUseCase {

    private final DnaGateway dnaGateway;
    private final NitrogenBaseUseCase nitrogenBaseUseCase;

    @Autowired
    public DnaUseCase(DnaGateway dnaGateway, NitrogenBaseUseCase nitrogenBaseUseCase) {
        this.dnaGateway = dnaGateway;
        this.nitrogenBaseUseCase = nitrogenBaseUseCase;
    }

    public boolean isSimian(DnaDomain dnaDomain) {
        dnaDomain.setDnaType(nitrogenBaseUseCase.checkDnaType(dnaDomain.getDnaSequence()));
        dnaGateway.postDnaSequence(dnaDomain);
        return dnaDomain.getDnaType().equals(DnaType.SIMIAN);
    }

    public DnaDomain getStats() {
        return dnaGateway.getStats().map(dnaDomain -> {
            dnaDomain.setRatio();
            return dnaDomain;
        }).orElseThrow(() -> new UseCaseException.NoRecordsFound("Unable to retrieve records."));
    }



}
