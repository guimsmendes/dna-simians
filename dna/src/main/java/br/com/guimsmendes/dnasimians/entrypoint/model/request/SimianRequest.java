package br.com.guimsmendes.dnasimians.entrypoint.model.request;

import br.com.guimsmendes.dnasimians.entrypoint.model.enums.NitrogenBaseType;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimianRequest {

    @NotNull
    @NotBlank
    List<String> dna;

    public DnaDomain asDnaDomain() {
        List<List<Character>> dnaSequence = new ArrayList<>();
        this.dna.forEach(s -> {
                    dnaSequence.add(NitrogenBaseType.fromString(s).orElseThrow(()-> new IllegalArgumentException("The input has to respect the Characters: A, T, C, G")));
                }
        );
        DnaDomain dnaDomain = new DnaDomain();
        dnaDomain.setDnaSequence(dnaSequence);
        return dnaDomain;
    }

}
