package br.com.guimsmendes.dnasimians.dataprovider.repository;

import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import br.com.guimsmendes.dnasimians.dataprovider.model.DnaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DnaRepositoryTest {

    @Autowired
    private DnaRepository dnaRepository;

    @Test
    void postDnaSequenceTest(){
        dnaRepository.delete();
        String dnaId = UUID.randomUUID().toString();
        Object response = dnaRepository.postDnaSequence(mockDnaEntityHuman(dnaId));
        assertEquals(dnaId,response.toString());
    }

    @Test
    void postDnaSequenceRepositoryExceptionTest(){
        Assertions.assertThrows(DataProviderException.RepositoryException.class,
                ()-> dnaRepository.postDnaSequence(null));
    }

    @Test
    void getStatsTest() {
        dnaRepository.delete();
        String dnaIdSimian = UUID.randomUUID().toString();
        dnaRepository.postDnaSequence(mockDnaEntitySimian(dnaIdSimian));
        String dnaIdHuman = UUID.randomUUID().toString();
        dnaRepository.postDnaSequence(mockDnaEntityHuman(dnaIdHuman));
        Object responseSimian = dnaRepository.count("Simian");
        assertEquals(BigInteger.valueOf(1), responseSimian);
        Object responseHuman = dnaRepository.count("Human");
        assertEquals(BigInteger.valueOf(1), responseHuman);
    }

    @Test
    void getStatsRepositoryExceptionTest(){
        Assertions.assertThrows(DataProviderException.RepositoryException.class,
                ()-> dnaRepository.count(null));
    }

    private DnaEntity mockDnaEntityHuman(String dnaId){
        return DnaEntity.builder()
                .dnaId(dnaId)
                .dnaType("HUMAN")
                .dnaSequence("(A, B, T, G)")
                .transactionDate(LocalDate.now())
                .build();}

    private DnaEntity mockDnaEntitySimian(String dnaId){
        return DnaEntity.builder()
                .dnaId(dnaId)
                .dnaType("SIMIAN")
                .dnaSequence("(A, B, T, G)")
                .transactionDate(LocalDate.now())
                .build();}

}