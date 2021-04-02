package br.com.guimsmendes.dnasimians.usecase.domain;

import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DnaDomain {

    private List<List<Character>> dnaSequence;
    private DnaType dnaType;
    private int countMutantDna;
    private int countHumanDna;
    private BigDecimal ratio;

    public DnaDomain(int countMutantDna, int countHumanDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
    }

    public void setRatio() {
        this.ratio = (this.getCountHumanDna() != 0) ? BigDecimal.valueOf(this.getCountMutantDna())
                .divide(BigDecimal.valueOf(this.getCountHumanDna()), 1, RoundingMode.HALF_EVEN) : BigDecimal.ONE;
    }
}
