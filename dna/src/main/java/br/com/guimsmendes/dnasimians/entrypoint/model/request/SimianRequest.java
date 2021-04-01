package br.com.guimsmendes.dnasimians.entrypoint.model.request;

import br.com.guimsmendes.dnasimians.entrypoint.model.enums.NitrogenBaseType;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.exception.UseCaseException;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
public class SimianRequest {
	
	@Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    @NotEmpty
    String[] dna;

    public DnaDomain asDnaDomain() {
    	List<String> dnaRow = convertArrayToString(dna);
        List<List<Character>> dnaSequence = new ArrayList<>();
        dnaRow.forEach(s -> {
                List<Character> dnaColumn = NitrogenBaseType.fromString(s).orElseThrow(() -> new IllegalArgumentException("The input has to respect the Characters: A, T, C, G"));
                if(dnaRow.size()!= dnaColumn.size()) {
                	throw new UseCaseException.InputOutOfBounds("The input has to respect the NxN size square table.");
                }
                dnaSequence.add(dnaColumn);
        }
        );
        DnaDomain dnaDomain = new DnaDomain();
        dnaDomain.setDnaSequence(dnaSequence);
        return dnaDomain;
    }
    
    private List<String> convertArrayToString(String array[]){
    	List<String> dnaList = new ArrayList<>();
    	for(String s : array) {
    		dnaList.add(s.replaceAll(" ", ""));
    	}
    	return dnaList;
    }

}
