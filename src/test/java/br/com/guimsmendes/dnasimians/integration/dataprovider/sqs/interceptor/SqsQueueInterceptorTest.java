package br.com.guimsmendes.dnasimians.integration.dataprovider.sqs.interceptor;

import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import br.com.guimsmendes.dnasimians.dataprovider.sqs.interceptor.SqsQueueInterceptor;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SqsQueueInterceptorTest {

    @Autowired
    private SqsQueueInterceptor sqsQueueInterceptor;

    @Value("${cloud.aws.sqs.endpoint}")
    private String sqsEndPoint;

    @Test
    void sendMessage() {
        assertFalse(sqsQueueInterceptor.send(mockDnaDomain()).get().isEmpty());
    }

    @Test
    void sqsSenderException(){
        Assertions.assertThrows(DataProviderException.SqsSenderException.class,
                () -> sqsQueueInterceptor.send(null));
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

}