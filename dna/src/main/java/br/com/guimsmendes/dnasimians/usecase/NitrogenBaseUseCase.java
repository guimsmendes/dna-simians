package br.com.guimsmendes.dnasimians.usecase;

import br.com.guimsmendes.dnasimians.usecase.domain.NitrogenBaseDomain;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import br.com.guimsmendes.dnasimians.usecase.exception.UseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NitrogenBaseUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(NitrogenBaseUseCase.class);

    public DnaType checkDnaType(List<List<Character>> dnaSequence) {
        LOGGER.info("Dna Type check is being processed...");
        NitrogenBaseDomain nitroBaseDomain = new NitrogenBaseDomain(dnaSequence);
        try {
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
        } catch (Exception e) {
            throw new UseCaseException.InputOutOfBounds("The input has to respect the NxN size square table.");
        }

    }

    private boolean checkNitrogenBases(NitrogenBaseDomain nitroBaseDomain) {

        return countNitrogenBases(nitroBaseDomain, -1, 0) + 1 +
                countNitrogenBases(nitroBaseDomain, 1, 0) >= 4
                || countNitrogenBases(nitroBaseDomain, 0, -1) + 1 +
                countNitrogenBases(nitroBaseDomain, 0, 1) >= 4
                || countNitrogenBases(nitroBaseDomain, -1, -1) + 1 +
                countNitrogenBases(nitroBaseDomain, 1, 1) >= 4
                || countNitrogenBases(nitroBaseDomain, -1, 1) + 1 +
                countNitrogenBases(nitroBaseDomain, 1, -1) >= 4;


    }


    private int countNitrogenBases(NitrogenBaseDomain nitroBaseDomain, int directionColumn, int directionRow) {
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
