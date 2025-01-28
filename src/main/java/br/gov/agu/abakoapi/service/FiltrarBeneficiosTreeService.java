package br.gov.agu.abakoapi.service;

import br.gov.agu.abakoapi.entities.BeneficioAtivoEntity;
import br.gov.agu.abakoapi.entities.BeneficioCessadoEntity;
import br.gov.agu.abakoapi.entities.CalculoEntity;
import br.gov.agu.abakoapi.enums.BeneficiosEnum;
import br.gov.agu.abakoapi.enums.StatusBeneficio;
import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class FiltrarBeneficiosTreeService {


    public CalculoEntity filtrarBeneficiosTree(JsonNode beneficiosTree, CalculoEntity calculoEntity) {
        LocalDate dataAjuizamento = calculoEntity.getDataAjuizamento();
        List<BeneficioCessadoEntity> beneficiosCessados = filtrarBeneficioCessado(beneficiosTree);
        List<BeneficioAtivoEntity> beneficiosAtivos = filtrarBeneficioAtivo(beneficiosTree,dataAjuizamento);
        calculoEntity.setBeneficioAtivos(beneficiosAtivos);
        calculoEntity.setBeneficioCessados(beneficiosCessados);

        return calculoEntity;
    }




    public List<BeneficioCessadoEntity> filtrarBeneficioCessado(JsonNode beneficiosTree) {

        List<JsonNode> beneficiosCessados = new ArrayList<>();
        List<BeneficioCessadoEntity> beneficiosCessadosEntity = new ArrayList<>();

        for (JsonNode beneficio : beneficiosTree) {
            StatusBeneficio statusBeneficio = StatusBeneficio.valueOf(beneficio.get("status").asText());
            if (statusBeneficio.equals(StatusBeneficio.CESSADO)) {
                beneficiosCessados.add(beneficio);
            }
        }

        mapJsonNodeToBeneficioCessado(beneficiosCessados, beneficiosCessadosEntity);

        return beneficiosCessadosEntity;
    }

    private static void mapJsonNodeToBeneficioCessado(List<JsonNode> beneficiosCessados, List<BeneficioCessadoEntity> beneficiosCessadosEntity) {
        for (JsonNode beneficio : beneficiosCessados) {
            BeneficioCessadoEntity beneficioCessadoEntity = new BeneficioCessadoEntity();
            BeneficiosEnum beneficioEnum = BeneficiosEnum.valueOf(beneficio.get("especie").asText());
            LocalDate inicioDesconto = OffsetDateTime.parse(beneficio.get("DIB").asText()).toLocalDate();
            LocalDate fimDesconto = OffsetDateTime.parse(beneficio.get("DIP").asText()).toLocalDate().minusDays(1);
            BigDecimal rmi = BigDecimal.valueOf(beneficio.get("RMI").asDouble());
            String nb = beneficio.get("NB").asText();
            LocalDate dib = OffsetDateTime.parse(beneficio.get("DIB").asText()).toLocalDate();
            LocalDate dibAnterior = OffsetDateTime.parse(beneficio.get("dataNBAnterior").asText()).toLocalDate();

            beneficioCessadoEntity.setBeneficio(beneficioEnum);
            beneficioCessadoEntity.setInicioDesconto(inicioDesconto);
            beneficioCessadoEntity.setFimDesconto(fimDesconto);
            beneficioCessadoEntity.setRmi(rmi);
            beneficioCessadoEntity.setPorcentagemRmi(null);
            beneficioCessadoEntity.setNb(nb);
            beneficioCessadoEntity.setDib(dib);
            beneficioCessadoEntity.setDibAnterior(dibAnterior);

            beneficiosCessadosEntity.add(beneficioCessadoEntity);
        }
    }


    public List<BeneficioAtivoEntity> filtrarBeneficioAtivo(JsonNode beneficiosTree, LocalDate dataAjuizamento) {

        List<JsonNode> beneficiosAtivos = new ArrayList<>();
        List<BeneficioAtivoEntity> beneficiosAtivosEntity = new ArrayList<>();


        for (JsonNode beneficio : beneficiosTree) {
            StatusBeneficio statusBeneficio = StatusBeneficio.valueOf(beneficio.get("status").asText());
            if (statusBeneficio.equals(StatusBeneficio.ATIVO)) {
                beneficiosAtivos.add(beneficio);
            }
        }

        mapJsonNodeToBeneficioAtivoEntity(beneficiosAtivos, beneficiosAtivosEntity,dataAjuizamento);
        return beneficiosAtivosEntity;
    }

    private static void mapJsonNodeToBeneficioAtivoEntity(List<JsonNode> beneficiosAtivos, List<BeneficioAtivoEntity> beneficiosAtivosEntity,LocalDate dataAjuizamento) {
        for (JsonNode beneficio: beneficiosAtivos){
            BeneficioAtivoEntity beneficioAtivoEntity = new BeneficioAtivoEntity();
            LocalDate dib = OffsetDateTime.parse(beneficio.get("DIB").asText()).toLocalDate();
            LocalDate inicioCalculo = dib;
            if (dib.isBefore(dataAjuizamento.minusYears(5))) {
                inicioCalculo = dataAjuizamento;
            }
            LocalDate fimCalculo = OffsetDateTime.parse(beneficio.get("DIP").asText()).toLocalDate().minusDays(1);
            BigDecimal rmi = BigDecimal.valueOf(beneficio.get("RMI").asDouble());

            LocalDate dibAnterior = OffsetDateTime.parse(beneficio.get("dataNBAnterior").asText()).toLocalDate();
            BeneficiosEnum beneficioEnum = BeneficiosEnum.valueOf(beneficio.get("especie").asText());
            String nb = beneficio.get("NB").asText();

            beneficioAtivoEntity.setInicioCalculo(inicioCalculo);
            beneficioAtivoEntity.setFimCalculo(fimCalculo);
            beneficioAtivoEntity.setInicioJuros(null);
            beneficioAtivoEntity.setRmi(rmi);
            beneficioAtivoEntity.setPorcentagemRmi(null);
            beneficioAtivoEntity.setDib(dib);
            beneficioAtivoEntity.setDibAnterior(dibAnterior);
            beneficioAtivoEntity.setBeneficio(beneficioEnum);
            beneficioAtivoEntity.setNb(nb);

            beneficiosAtivosEntity.add(beneficioAtivoEntity);
        }
    }
}
