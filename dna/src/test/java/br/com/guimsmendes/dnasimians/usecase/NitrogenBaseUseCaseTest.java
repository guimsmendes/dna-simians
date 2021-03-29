package br.com.guimsmendes.dnasimians.usecase;

import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NitrogenBaseUseCaseTest {

    @InjectMocks
    private NitrogenBaseUseCase nitrogenBaseUseCase;

    @Test
    public void checkSimianDnaTypeHorizontalTest() {
        DnaType dnaType = nitrogenBaseUseCase.checkDnaType(mockSimianDnaSequenceHorizontal());
        assertEquals(DnaType.SIMIAN, dnaType);
    }

    @Test
    public void checkSimianDnaTypeVerticalTest() {
        DnaType dnaType = nitrogenBaseUseCase.checkDnaType(mockSimianDnaSequenceVertical());
        assertEquals(DnaType.SIMIAN, dnaType);
    }

    @Test
    public void checkSimianDnaTypeDiagonalUpperTest() {
        DnaType dnaType = nitrogenBaseUseCase.checkDnaType(mockSimianDnaSequenceDiagonalUpperDirection());
        assertEquals(DnaType.SIMIAN, dnaType);
    }


    @Test
    public void checkSimianDnaTypeDiagonalDownerTest() {
        DnaType dnaType = nitrogenBaseUseCase.checkDnaType(mockSimianDnaSequenceDiagonalDownerDirection());
        assertEquals(DnaType.SIMIAN, dnaType);
    }

    @Test
    public void checkHumanDnaTypeTest() {
        DnaType dnaType = nitrogenBaseUseCase.checkDnaType(mockHumanDnaSequence());
        assertEquals(DnaType.HUMAN, dnaType);
    }

    private List<List<Character>> mockSimianDnaSequenceHorizontal(){
        List<Character> dnaList = new ArrayList<>();
        int i;
        for (i = 0; i < 4; i++) {
            dnaList.add('A');
        }
        dnaList.add('G');
        List<List<Character>> dnaSequence = new ArrayList<>();
        dnaSequence.add(dnaList);
        return dnaSequence;
    }

    private List<List<Character>> mockSimianDnaSequenceVertical(){
        List<Character> dnaList = new ArrayList<>();
        int i;
        dnaList.add('A');
        dnaList.add('G');
        dnaList.add('C');
        dnaList.add('T');

        List<List<Character>> dnaSequence = new ArrayList<>();
        for(i =0 ; i< 4; i++){
            dnaSequence.add(dnaList);
        }
        return dnaSequence;

    }
    private List<List<Character>> mockSimianDnaSequenceDiagonalUpperDirection(){
        List<Character> dnaList = new ArrayList<>();
        dnaList.add('A');
        dnaList.add('G');
        dnaList.add('C');
        dnaList.add('T');
        List<Character> dnaList2 = new ArrayList<>();
        dnaList2.add('T');
        dnaList2.add('A');
        dnaList2.add('C');
        dnaList2.add('T');
        List<Character> dnaList3 = new ArrayList<>();
        dnaList3.add('G');
        dnaList3.add('C');
        dnaList3.add('A');
        dnaList3.add('T');
        List<Character> dnaList4 = new ArrayList<>();
        dnaList4.add('A');
        dnaList4.add('G');
        dnaList4.add('C');
        dnaList4.add('A');

        List<List<Character>> dnaSequence = new ArrayList<>();
        dnaSequence.add(dnaList);
        dnaSequence.add(dnaList2);
        dnaSequence.add(dnaList3);
        dnaSequence.add(dnaList4);

        return dnaSequence;
    }
    private List<List<Character>> mockSimianDnaSequenceDiagonalDownerDirection(){
        List<Character> dnaList = new ArrayList<>();
        dnaList.add('T');
        dnaList.add('G');
        dnaList.add('C');
        dnaList.add('A');
        List<Character> dnaList2 = new ArrayList<>();
        dnaList2.add('T');
        dnaList2.add('C');
        dnaList2.add('A');
        dnaList2.add('T');
        List<Character> dnaList3 = new ArrayList<>();
        dnaList3.add('G');
        dnaList3.add('A');
        dnaList3.add('T');
        dnaList3.add('T');
        List<Character> dnaList4 = new ArrayList<>();
        dnaList4.add('A');
        dnaList4.add('G');
        dnaList4.add('C');
        dnaList4.add('A');

        List<List<Character>> dnaSequence = new ArrayList<>();
        dnaSequence.add(dnaList);
        dnaSequence.add(dnaList2);
        dnaSequence.add(dnaList3);
        dnaSequence.add(dnaList4);

        return dnaSequence;
    }

    private List<List<Character>> mockHumanDnaSequence(){
        List<Character> dnaList = new ArrayList<>();
        dnaList.add('A');
        dnaList.add('A');
        dnaList.add('A');
        dnaList.add('T');

        List<Character> dnaList2 = new ArrayList<>();
        dnaList2.add('T');
        dnaList2.add('A');
        dnaList2.add('A');
        dnaList2.add('C');

        List<Character> dnaList3 = new ArrayList<>();
        dnaList3.add('T');
        dnaList3.add('G');
        dnaList3.add('A');
        dnaList3.add('C');

        List<Character> dnaList4 = new ArrayList<>();
        dnaList4.add('T');
        dnaList4.add('G');
        dnaList4.add('C');
        dnaList4.add('T');

        List<List<Character>> dnaSequence = new ArrayList<>();
        dnaSequence.add(dnaList);
        dnaSequence.add(dnaList2);
        dnaSequence.add(dnaList3);
        dnaSequence.add(dnaList4);
        return dnaSequence;
    }

}