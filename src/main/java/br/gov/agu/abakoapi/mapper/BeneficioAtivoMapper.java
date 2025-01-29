package br.gov.agu.abakoapi.mapper;

import br.gov.agu.abakoapi.dto.BeneficioAtivoDTO;
import br.gov.agu.abakoapi.entities.BeneficioAtivoEntity;
import org.mapstruct.Mapper;


@Mapper
public interface BeneficioAtivoMapper {

    BeneficioAtivoDTO toBeneficioAtivoDTO(BeneficioAtivoEntity beneficioAtivo);



}
