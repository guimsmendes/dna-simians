package br.com.guimsmendes.dnasimians.entrypoint.mapper;

import org.mapstruct.Mapper;

import br.com.guimsmendes.dnasimians.entrypoint.model.response.StatsResponse;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;

@Mapper(componentModel = "spring")
public interface StatsMapper {

    StatsResponse toStatsResponse(DnaDomain dnaDomain);
}
