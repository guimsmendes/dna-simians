package br.com.guimsmendes.dnasimians.usecase.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class NitrogenBaseDomain {

    private final List<List<Character>> dnaSequence;
    private final int numberOfColumns;
    private final int numberOfRows;
    private int currentRow;
    private int currentColumn;
    private char nitrogenBase;

    public NitrogenBaseDomain(List<List<Character>> dnaSequence){
        this.dnaSequence = dnaSequence;
        this.numberOfColumns = dnaSequence.size();
        this.numberOfRows = dnaSequence.get(0).size();
        this.currentColumn = 0;
        this.currentRow = 0;
    }

    public void setNitrogenBase(char nitrogenBase){
        this.nitrogenBase = nitrogenBase;
    }

    public void addRow(){
        this.currentRow++;
    }

    public void addColumn(){
        this.currentColumn++;
    }
}
