package br.gov.agu.abakoapi.mapper;

import br.gov.agu.abakoapi.dto.FiltroResponseDTO;
import br.gov.agu.abakoapi.entities.CalculoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CalculoMapper {



    FiltroResponseDTO toFiltroResponseDTO(CalculoEntity calculoEntity);
}
