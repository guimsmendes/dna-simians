package br.com.guimsmendes.dnasimians.entrypoint.mapper;

import br.com.guimsmendes.dnasimians.entrypoint.model.response.StatsResponse;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class StatsMapperTest {

    private StatsMapper statsMapper;

    @BeforeEach
    public void init(){
        statsMapper = new StatsMapperImpl();
    }

    @Test
    void toStatsResponse(){
        StatsResponse statsResponse = statsMapper.toStatsResponse(mockDnaDomain());
        assertEquals(40, statsResponse.getCountMutantDna());
        assertEquals(100, statsResponse.getCountHumanDna());
        assertEquals(BigDecimal.valueOf(0.4), statsResponse.getRatio());

    }
    
    @Test
    void toStatsResponseNull() {
    	assertNull(statsMapper.toStatsResponse(null));
    }

    private DnaDomain mockDnaDomain(){
        DnaDomain dnaDomain = new DnaDomain();
        dnaDomain.setCountHumanDna(100);
        dnaDomain.setCountMutantDna(40);
        dnaDomain.setRatio(BigDecimal.valueOf(0.4));
        return dnaDomain;
    }

}