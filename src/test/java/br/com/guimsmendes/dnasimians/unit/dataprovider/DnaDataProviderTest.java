package br.com.guimsmendes.dnasimians.unit.dataprovider;

import br.com.guimsmendes.dnasimians.dataprovider.DnaDataProvider;
import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import br.com.guimsmendes.dnasimians.dataprovider.repository.DnaRepository;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DnaDataProviderTest {
    @InjectMocks
    private DnaDataProvider dnaDataProvider;

    @Mock
    private DnaRepository dnaRepository;

    @Test
    void getValidStats() {
        when(dnaRepository.count(DnaType.HUMAN.toString())).thenReturn(BigInteger.valueOf(100));
        when(dnaRepository.count(DnaType.SIMIAN.toString())).thenReturn(BigInteger.valueOf(40));

        Optional<DnaDomain> dnaDomain = dnaDataProvider.getStats();
            assertEquals(100, dnaDomain.get().getCountHumanDna());
            assertEquals(40, dnaDomain.get().getCountMutantDna());
    }


    @Test
    void unableToConnectDatabase() {
        when(dnaRepository.count(any(String.class))).thenThrow(new DataProviderException
                .UnableToSaveRecord("Unable to connect."));

        Optional<DnaDomain> dnaDomain = dnaDataProvider.getStats();
        assertEquals(Optional.empty(), dnaDomain);
    }
}