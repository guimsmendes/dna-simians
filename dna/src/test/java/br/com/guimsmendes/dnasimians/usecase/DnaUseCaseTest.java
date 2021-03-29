package br.com.guimsmendes.dnasimians.usecase;

import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import br.com.guimsmendes.dnasimians.usecase.exception.UseCaseException;
import br.com.guimsmendes.dnasimians.usecase.gateway.DnaGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DnaUseCaseTest {

    @InjectMocks
    private DnaUseCase dnaUseCase;

    @Mock
    private DnaGateway dnaGateway;

    @Mock
    private NitrogenBaseUseCase nitrogenBaseUseCase;

    @Test
    void isSimianTrue() {
        when(nitrogenBaseUseCase.checkDnaType(any(List.class))).thenReturn(DnaType.SIMIAN);
        boolean response = dnaUseCase.isSimian(mockDnaDomain());
        assertTrue(response);
    }

    @Test
    void isSimianFalse() {
        when(nitrogenBaseUseCase.checkDnaType(any(List.class))).thenReturn(DnaType.HUMAN);
        boolean response = dnaUseCase.isSimian(mockDnaDomain());
        assertFalse(response);
    }

    @Test
    void getValidStats() {
        when(dnaGateway.getStats()).thenReturn(mockOptionalDnaCounts());
        DnaDomain dnaDomain = dnaUseCase.getStats();
        assertEquals(40, dnaDomain.getCountMutantDna());
        assertEquals(100, dnaDomain.getCountHumanDna());
        assertEquals(BigDecimal.valueOf(0.4), dnaDomain.getRatio());
    }

    @Test
    void getNoHumanStats() {
        when(dnaGateway.getStats()).thenReturn(mockOptionalDnaNoHumanCount());
        DnaDomain dnaDomain = dnaUseCase.getStats();
        assertEquals(40, dnaDomain.getCountMutantDna());
        assertEquals(0, dnaDomain.getCountHumanDna());
        assertEquals(BigDecimal.valueOf(1), dnaDomain.getRatio());
    }

    @Test
    void getNoRecordsFound() {
        when(dnaGateway.getStats()).thenReturn(Optional.empty());

        Assertions.assertThrows(UseCaseException.NoRecordsFound.class,
                () -> dnaUseCase.getStats());

    }

    private DnaDomain mockDnaDomain(){
        List<Character> dnaList = new ArrayList<>();
        int i;
        for(i =0 ; i< 4; i++){
            dnaList.add('A');
        }
        dnaList.add('G');
        List<List<Character>> dnaSequence = new ArrayList<>();
        dnaSequence.add(dnaList);
        DnaDomain dnaDomain = new DnaDomain();
        dnaDomain.setDnaSequence(dnaSequence);
        return dnaDomain;
    }

    private Optional<DnaDomain> mockOptionalDnaCounts(){
        DnaDomain dnaDomain = new DnaDomain();
        dnaDomain.setCountHumanDna(100);
        dnaDomain.setCountMutantDna(40);
        return Optional.of(dnaDomain);
    }

    private Optional<DnaDomain> mockOptionalDnaNoHumanCount(){
        DnaDomain dnaDomain = new DnaDomain();
        dnaDomain.setCountHumanDna(0);
        dnaDomain.setCountMutantDna(40);
        return Optional.of(dnaDomain);
    }

}