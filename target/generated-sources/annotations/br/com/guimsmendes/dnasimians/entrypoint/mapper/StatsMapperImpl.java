package br.com.guimsmendes.dnasimians.entrypoint.mapper;

import br.com.guimsmendes.dnasimians.entrypoint.model.response.StatsResponse;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import java.math.BigDecimal;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-02T09:20:48-0300",
    comments = "version: 1.4.0.Beta3, compiler: javac, environment: Java 11.0.10 (Ubuntu)"
)
@Component
public class StatsMapperImpl implements StatsMapper {

    @Override
    public StatsResponse toStatsResponse(DnaDomain dnaDomain) {
        if ( dnaDomain == null ) {
            return null;
        }

        int countMutantDna = 0;
        int countHumanDna = 0;
        BigDecimal ratio = null;

        countMutantDna = dnaDomain.getCountMutantDna();
        countHumanDna = dnaDomain.getCountHumanDna();
        ratio = dnaDomain.getRatio();

        StatsResponse statsResponse = new StatsResponse( countMutantDna, countHumanDna, ratio );

        return statsResponse;
    }
}
