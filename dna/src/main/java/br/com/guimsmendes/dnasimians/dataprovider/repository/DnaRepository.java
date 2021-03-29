package br.com.guimsmendes.dnasimians.dataprovider.repository;

import org.springframework.stereotype.Repository;

import br.com.guimsmendes.dnasimians.dataprovider.model.DnaEntity;
import br.com.guimsmendes.dnasimians.usecase.domain.enums.DnaType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface DnaRepository extends JpaRepository<DnaEntity, Long>{
	
	@Query("SELECT COUNT(u) FROM dna u WHERE 'dnaType' = ?1")
	Optional<Integer> count(DnaType dnaType);
	
}
