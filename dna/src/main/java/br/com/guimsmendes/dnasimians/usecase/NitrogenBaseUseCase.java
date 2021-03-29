package br.com.guimsmendes.dnasimians.usecase;

import br.com.guimsmendes.dnasimians.usecase.domain.NitrogenBaseDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NitrogenBaseUseCase {

    public DnaType checkDnaType(List<List<Character>> dnaSequence) {
        NitrogenBaseDomain nitroBaseDomain = new NitrogenBaseDomain(dnaSequence);
        for (List<Character> dnaColumn : dnaSequence) {
            for (Character dnaChar : dnaColumn) {
                nitroBaseDomain.setNitrogenBase(dnaChar);
                if (this.checkNitrogenBases(nitroBaseDomain)) {
                    return DnaType.SIMIAN;
                }
                nitroBaseDomain.addRow();
            }
            nitroBaseDomain.addColumn();
        }
        return DnaType.HUMAN;
    }

    private boolean checkNitrogenBases(NitrogenBaseDomain nitroBaseDomain) {
        return count(nitroBaseDomain, -1, 0) + 1 +
                count(nitroBaseDomain, 1, 0) >= 4
                || count(nitroBaseDomain, 0, -1) + 1 +
                count(nitroBaseDomain, 0, 1) >= 4
                || count(nitroBaseDomain, -1, -1) + 1 +
                count(nitroBaseDomain, 1, 1) >= 4
                || count(nitroBaseDomain, -1, 1) + 1 +
                count(nitroBaseDomain, 1, -1) >= 4;
    }


    private int count(NitrogenBaseDomain nitroBaseDomain, int directionColumn, int directionRow) {
        int count = 0;
        int column = nitroBaseDomain.getCurrentColumn() + directionColumn;
        int row = nitroBaseDomain.getCurrentRow() + directionRow;
        while (column >= 0 && column < nitroBaseDomain.getNumberOfColumns() && row >= 0 && row < nitroBaseDomain.getNumberOfRows() &&
                nitroBaseDomain.getDnaSequence().get(column).get(row) == nitroBaseDomain.getNitrogenBase()) {
            count++;
            column += directionColumn;
            row += directionRow;
        }
        return count;
    }

}
