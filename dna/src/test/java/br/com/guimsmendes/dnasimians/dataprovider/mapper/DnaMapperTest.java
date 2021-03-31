package br.com.guimsmendes.dnasimians.dataprovider.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.guimsmendes.dnasimians.dataprovider.model.DnaEntity;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;

@ExtendWith(SpringExtension.class)
class DnaMapperTest {
	
	private DnaMapper dnaMapper;
	
    @BeforeEach
    public void init(){
    	dnaMapper = new DnaMapperImpl();
    }

    @Test
    void toDnaEntity(){
        DnaEntity dnaEntity = dnaMapper.toDnaEntity(mockDnaDomain());
        assertEquals(DnaType.HUMAN.toString(), dnaEntity.getDnaType());
        assertEquals("[[A, A, A, A, G], [A, A, A, A, G]]", dnaEntity.getDnaSequence());
        assertEquals(String.class, dnaEntity.getDnaId().getClass());
        assertEquals(LocalDate.now(), dnaEntity.getTransactionDate());

    }
    
    @Test
    void toDnaEntityNull() {
    	assertNull(dnaMapper.toDnaEntity(null));
    }

    private DnaDomain mockDnaDomain(){
        DnaDomain dnaDomain = new DnaDomain();
        dnaDomain.setDnaType(DnaType.HUMAN);
        List<Character> dnaList = new ArrayList<>();
        int i;
        for(i =0 ; i< 4; i++){
            dnaList.add('A');
        }
        dnaList.add('G');
        List<Character> dnaList1 = new ArrayList<>();     
        for(i =0 ; i< 4; i++){
        	dnaList1.add('A');
        }
        dnaList1.add('G');
        List<List<Character>> dnaSequence = new ArrayList<>();
        dnaSequence.add(dnaList);
        dnaSequence.add(dnaList1);
        dnaDomain.setDnaSequence(dnaSequence);
        return dnaDomain;
    }
}