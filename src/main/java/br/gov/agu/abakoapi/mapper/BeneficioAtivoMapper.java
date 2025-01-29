package br.gov.agu.abakoapi.mapper;

import br.gov.agu.abakoapi.dto.BeneficioAtivoDTO;
import br.gov.agu.abakoapi.entities.BeneficioAtivoEntity;
import br.gov.agu.abakoapi.enums.BeneficiosEnum;
import com.fasterxml.jackson.databind.JsonNode;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;


@Mapper
public interface BeneficioAtivoMapper {


    BeneficioAtivoDTO toBeneficioAtivoDTO(BeneficioAtivoEntity beneficioAtivo);

    @Mapping(target = "beneficio",source = "beneficioAtivo", qualifiedByName = "extractEspecieBeneficio")
    BeneficioAtivoEntity mapToBeneficioAtivoEntity(JsonNode beneficioAtivo);

    @Named("extractEspecieBeneficio")
    default BeneficiosEnum extractEspecieBeneficio(JsonNode beneficioAtivo) {
        return BeneficiosEnum.valueOf(beneficioAtivo.get("especie").asText());
    }

    @Named("extractNb")
    default String extractNb(JsonNode beneficioAtivo) {
        return beneficioAtivo.get("NB").asText();
    }

    @Named("extractDib")
    default LocalDate extractDib(JsonNode beneficioAtivo) {
        return OffsetDateTime.parse(beneficioAtivo.get("DIB").asText()).toLocalDate();
    }

    @Named("extractFimCalculo")
    default LocalDate extractFimCalculo(JsonNode beneficioAtivo) {
        return OffsetDateTime.parse(beneficioAtivo.get("DIP").asText()).toLocalDate().minusDays(1L);
    }

    @Name("extractInicioCalculo")
    default LocalDate extractInicioCalculo(JsonNode beneficioAtivo) {
        return OffsetDateTime.parse(beneficioAtivo.get("DIB").asText()).toLocalDate();
    }

    @Named("extractRmi")
    default BigDecimal extractRmi(JsonNode beneficioAtivo) {
        return BigDecimal.valueOf(beneficioAtivo.get("RMI").asDouble());
    }

    @Named("extractCoeficiente")
    default Integer extractCoeficiente(JsonNode beneficioAtivo) {
        return beneficioAtivo.get("coeficiente").asInt();
    }

    @Named("extractDibAnterior")
    default LocalDate extractDibAnterior(JsonNode beneficioAtivo) {
        return OffsetDateTime.parse(beneficioAtivo.get("dataNBAnterior").asText()).toLocalDate();
    }

}
