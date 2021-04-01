package br.com.guimsmendes.dnasimians.unit.dataprovider.sqs.listener;

import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import br.com.guimsmendes.dnasimians.dataprovider.mapper.DnaMapper;
import br.com.guimsmendes.dnasimians.dataprovider.model.DnaEntity;
import br.com.guimsmendes.dnasimians.dataprovider.repository.DnaRepository;
import br.com.guimsmendes.dnasimians.dataprovider.sqs.listener.SQSQueueListener;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static br.com.guimsmendes.dnasimians.dataprovider.constant.Constants.CORRELATION_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SQSQueueListenerTest {

    @InjectMocks
    private SQSQueueListener sqsQueueListener;

    @Mock
    private DnaRepository dnaRepository;

    @Mock
    private DnaMapper dnaMapper;

    @Test
    void getUnableToSaveRecordException() {
        when(dnaMapper.toDnaEntity(any(DnaDomain.class))).thenReturn(mockDnaEntity());
        when(dnaRepository.postDnaSequence(any(DnaEntity.class))).thenReturn("");
        Assertions.assertThrows(DataProviderException.UnableToSaveRecord.class,
                ()-> sqsQueueListener.dnaSequenceListener(mockDnaDomain(), mockHeader()));
    }

    private Map<String, Object> mockHeader(){
        UUID correlationId = UUID.randomUUID();
        Map<String, Object> headers = new HashMap<>();
        headers.put(CORRELATION_ID, correlationId.toString());
        return headers;
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
        dnaDomain.setDnaType(DnaType.SIMIAN);
        return dnaDomain;
    }

    private DnaEntity mockDnaEntity(){
        return DnaEntity.builder()
                .dnaId("60c1ff9e-6848-4187-b3c8-a8b72cecd01b")
                .dnaType("HUMAN")
                .dnaSequence("[A, T, C, G]")
                .transactionDate(LocalDate.now())
                .build();
    }

}