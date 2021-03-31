package br.com.guimsmendes.dnasimians.unit.entrypoint.controller;

import br.com.guimsmendes.dnasimians.entrypoint.controller.SimianController;
import br.com.guimsmendes.dnasimians.entrypoint.mapper.StatsMapper;
import br.com.guimsmendes.dnasimians.entrypoint.model.request.SimianRequest;
import br.com.guimsmendes.dnasimians.entrypoint.model.response.StatsResponse;
import br.com.guimsmendes.dnasimians.usecase.DnaUseCase;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SimianControllerTest {

    @InjectMocks
    private SimianController simianController;

    @Mock
    private DnaUseCase dnaUseCase;

    @Mock
    private StatsMapper statsMapper;


    @Test
    void isSimianOk() {
        when(dnaUseCase.isSimian(any(DnaDomain.class))).thenReturn(true);
        ResponseEntity<Object> result = simianController.isSimian(mockValidSimianRequest());
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.OK).build());
    }

    @Test
    void isSimianForbidden() {
        when(dnaUseCase.isSimian(any(DnaDomain.class))).thenReturn(false);
        ResponseEntity<?> result = simianController.isSimian(mockValidSimianRequest());
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @Test
    void validRequestBody() {
        SimianRequest simianRequest = mockValidSimianRequest();
        DnaDomain dnaDomain = simianRequest.asDnaDomain();
        assertEquals(dnaDomain.getDnaSequence(), mockDnaSequence());
    }

    @Test
    void invalidRequestBody() {
        SimianRequest simianRequest = mockInvalidSimianRequest();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> simianRequest.asDnaDomain());
    }

    @Test
    void getStats() {
        when(dnaUseCase.getStats()).thenReturn(mockDnaDomain());
        when(statsMapper.toStatsResponse(any(DnaDomain.class))).thenReturn(mockStatsResponse());
        ResponseEntity<StatsResponse> result = simianController.getStats();
        assertEquals(result.getStatusCodeValue(), ResponseEntity.status(HttpStatus.OK).build().getStatusCodeValue());
        assertEquals(40, Objects.requireNonNull(result.getBody()).getCountMutantDna());
        assertEquals(100, Objects.requireNonNull(result.getBody()).getCountHumanDna());
        assertEquals(BigDecimal.valueOf(0.4), Objects.requireNonNull(result.getBody()).getRatio());
    }
    private SimianRequest mockValidSimianRequest(){
        List<String> dnaSequence = new LinkedList<>();
        dnaSequence.add("ATCGA");
        dnaSequence.add("CGTAG");
        return new SimianRequest(dnaSequence);
    }

    private SimianRequest mockInvalidSimianRequest(){
        List<String> dnaSequence = new LinkedList<>();
        dnaSequence.add("ATCJJ");
        dnaSequence.add("CGFKG");
        return new SimianRequest(dnaSequence);
    }
    private DnaDomain mockDnaDomain(){
        DnaDomain dnaDomain = new DnaDomain();
        dnaDomain.setCountMutantDna(40);
        dnaDomain.setCountHumanDna(100);
        dnaDomain.setRatio(BigDecimal.valueOf(0.4));
        return dnaDomain;
    }
    private StatsResponse mockStatsResponse(){
        return StatsResponse.builder()
                .countHumanDna(100)
                .countMutantDna(40)
                .ratio(BigDecimal.valueOf(0.4))
                .build();
    }

    private List<List<Character>> mockDnaSequence(){
        List<Character> dnaList = new ArrayList<>();
        dnaList.add('A');
        dnaList.add('T');
        dnaList.add('C');
        dnaList.add('G');
        dnaList.add('A');
        List<Character> dnaList1 = new ArrayList<>();
        dnaList1.add('C');
        dnaList1.add('G');
        dnaList1.add('T');
        dnaList1.add('A');
        dnaList1.add('G');
        List<List<Character>> dnaSequence = new ArrayList<>();
        dnaSequence.add(dnaList);
        dnaSequence.add(dnaList1);
        return dnaSequence;
    }
}