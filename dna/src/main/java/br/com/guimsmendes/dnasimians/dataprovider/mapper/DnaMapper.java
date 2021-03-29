package br.com.guimsmendes.dnasimians.dataprovider.mapper;

import br.com.guimsmendes.dnasimians.dataprovider.model.DnaEntity;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public class DnaMapper {
    public DnaEntity toDnaEntity(DnaDomain dnaDomain) {
        return DnaEntity.builder()
                .dnaId(UUID.randomUUID())
                .dnaType(dnaDomain.getDnaType())
                .dnaSequence(dnaDomain.getDnaSequence()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
                .build();
    }


}
