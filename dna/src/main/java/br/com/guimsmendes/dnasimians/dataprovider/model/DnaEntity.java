package br.com.guimsmendes.dnasimians.dataprovider.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "dna")
public class DnaEntity {
	
	@Id
	@Column(length = 36, nullable = false, unique = true)
	String dnaId;
	
	@Column(length = 20, nullable = false, updatable = false)
	String dnaType;
	
    @Column(length = 255, nullable = false)
	String dnaSequence;

    @Column(columnDefinition = "DATE", nullable = false)
	LocalDate transactionDate;

}
