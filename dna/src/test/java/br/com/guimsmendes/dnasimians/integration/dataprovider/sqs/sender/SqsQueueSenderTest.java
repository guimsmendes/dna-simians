package br.com.guimsmendes.dnasimians.integration.dataprovider.sqs.sender;

import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import br.com.guimsmendes.dnasimians.dataprovider.sqs.sender.SqsQueueSender;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SqsQueueSenderTest {

    @Autowired
    private SqsQueueSender sqsQueueSender;

    @Value("${cloud.aws.sqs.endpoint}")
    private String sqsEndPoint;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Test
    void sendMessage() {
        sqsQueueSender.send(mockDnaDomain());
        DnaDomain dnaDomain = this.queueMessagingTemplate.receiveAndConvert(sqsEndPoint, DnaDomain.class);
        assertEquals(DnaType.SIMIAN, dnaDomain.getDnaType());
    }

    @Test
    void sqsSenderException(){
        Assertions.assertThrows(DataProviderException.SqsSenderException.class,
                () -> sqsQueueSender.send(null));
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