package br.com.guimsmendes.dnasimians.dataprovider.mapper;

import br.com.guimsmendes.dnasimians.dataprovider.model.DnaEntity;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports= {UUID.class, LocalDate.class})
public interface DnaMapper {
	
	@Mapping(target="dnaId", expression = "java(String.valueOf(UUID.randomUUID()))")
    @Mapping(target="transactionDate", expression= "java(LocalDate.now())")
    DnaEntity toDnaEntity(DnaDomain dnaDomain);

    default String map(List<List<Character>> source) {
    	return String.valueOf(source);
    }
}
