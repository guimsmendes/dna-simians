package br.com.guimsmendes.dnasimians.dataprovider.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dna")
public class DnaEntity {
	
	@Id
	@Column(length = 36, nullable = false, unique = true)
	UUID dnaId;
	
	@Column(length = 20, nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	DnaType dnaType;
	
    @Column(length = 255, nullable = false)
	String dnaSequence;

}
