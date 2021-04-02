package br.com.guimsmendes.dnasimians.dataprovider.mapper;

import br.com.guimsmendes.dnasimians.dataprovider.model.DnaEntity;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-02T09:20:48-0300",
    comments = "version: 1.4.0.Beta3, compiler: javac, environment: Java 11.0.10 (Ubuntu)"
)
@Component
public class DnaMapperImpl implements DnaMapper {

    @Override
    public DnaEntity toDnaEntity(DnaDomain dnaDomain) {
        if ( dnaDomain == null ) {
            return null;
        }

        String dnaType = null;
        String dnaSequence = null;

        if ( dnaDomain.getDnaType() != null ) {
            dnaType = dnaDomain.getDnaType().name();
        }
        dnaSequence = map( dnaDomain.getDnaSequence() );

        String dnaId = String.valueOf(UUID.randomUUID());
        LocalDate transactionDate = LocalDate.now();

        DnaEntity dnaEntity = new DnaEntity( dnaId, dnaType, dnaSequence, transactionDate );

        return dnaEntity;
    }
}
