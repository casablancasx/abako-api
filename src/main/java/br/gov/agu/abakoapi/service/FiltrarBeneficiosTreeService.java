package br.gov.agu.abakoapi.service;

import br.gov.agu.abakoapi.entities.*;
import br.gov.agu.abakoapi.enums.BeneficiosEnum;
import br.gov.agu.abakoapi.enums.StatusBeneficio;
import br.gov.agu.abakoapi.repositories.BeneficioRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FiltrarBeneficiosTreeService {


    private final BeneficioRepository beneficioRepository;

    public CalculoEntity filtrarBeneficiosTree(JsonNode beneficiosTree, CalculoEntity calculoEntity) {
        LocalDate dataAjuizamento = calculoEntity.getDataAjuizamento();
        List<BeneficioAtivoEntity> beneficiosAtivos = filtrarBeneficioAtivo(beneficiosTree,dataAjuizamento);

        for (BeneficioAtivoEntity beneficioAtivo : beneficiosAtivos) {
            List<String> nomesBeneficiosInacumuladosDoAtivo = buscarNomesBeneficiosInacumulados(beneficioAtivo.getBeneficio());
            List<BeneficioCessadoEntity> beneficioCessados = buscarBeneficioCessadosInacumulados(nomesBeneficiosInacumuladosDoAtivo, beneficioAtivo, beneficiosTree);
            beneficioAtivo.setBeneficioCessados(beneficioCessados);
        }

        calculoEntity.setBeneficioAtivos(beneficiosAtivos);
        return calculoEntity;
    }

    private List<BeneficioCessadoEntity> buscarBeneficioCessadosInacumulados(List<String> nomesBeneficiosInacumuladosDoAtivo, BeneficioAtivoEntity beneficioAtivo, JsonNode beneficiosTree) {
        List<BeneficioCessadoEntity> beneficioCessadoEntities = new ArrayList<>();

        for(JsonNode beneficio: beneficiosTree){
            StatusBeneficio status = StatusBeneficio.valueOf(beneficio.get("status").asText());
            LocalDate inicioDesconto = OffsetDateTime.parse(beneficio.get("DIB").asText()).toLocalDate();
            LocalDate inicioCalculo = beneficioAtivo.getInicioCalculo();
            LocalDate fimCalculo = beneficioAtivo.getFimCalculo();

            if (status.equals(StatusBeneficio.CESSADO) && isInicioDescontoDentroPeriodoDoBeneficioAtivo(inicioDesconto, inicioCalculo, fimCalculo)){
                BeneficioCessadoEntity beneficioCessado = new BeneficioCessadoEntity();
                beneficioCessado.setBeneficio(BeneficiosEnum.valueOf(beneficio.get("especie").asText()));
                beneficioCessado.setInicioDesconto(inicioDesconto);
                beneficioCessado.setFimDesconto(OffsetDateTime.parse(beneficio.get("DIP").asText()).toLocalDate().minusDays(1));
                beneficioCessado.setRmi(BigDecimal.valueOf(beneficio.get("RMI").asDouble()));
                beneficioCessado.setPorcentagemRmi(null);
                beneficioCessado.setNb(beneficio.get("NB").asText());
                beneficioCessado.setDib(OffsetDateTime.parse(beneficio.get("DIB").asText()).toLocalDate());
                beneficioCessado.setDibAnterior(OffsetDateTime.parse(beneficio.get("dataNBAnterior").asText()).toLocalDate());
                beneficioCessado.setBeneficioAtivo(beneficioAtivo);

                beneficioCessadoEntities.add(beneficioCessado);
            }


        }
        return beneficioCessadoEntities;
    }

    private boolean isInicioDescontoDentroPeriodoDoBeneficioAtivo(LocalDate inicioDesconto, LocalDate inicioCalculo, LocalDate fimCalculo) {
        return inicioDesconto.isAfter(inicioCalculo) && inicioDesconto.isBefore(fimCalculo);
    }


    private List<String> buscarNomesBeneficiosInacumulados(BeneficiosEnum beneficioEnum) {
        return beneficioRepository.findByNome(beneficioEnum.getNome())
                .stream()
                .map(BeneficioEntity::getNome)
                .toList();
    }


    private List<BeneficioAtivoEntity> filtrarBeneficioAtivo(JsonNode beneficiosTree, LocalDate dataAjuizamento) {

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
