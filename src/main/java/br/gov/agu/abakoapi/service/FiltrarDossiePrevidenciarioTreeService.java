package br.gov.agu.abakoapi.service;


import br.gov.agu.abakoapi.entities.CalculoEntity;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
public class FiltrarDossiePrevidenciarioTreeService {


    public CalculoEntity filtrarDossiePrevidenciarioTree(CalculoEntity calculoEntity, JsonNode dossiePrevicendiarioTree) {

        String nomeParte = dossiePrevicendiarioTree.get("nome").asText();

        String numeroProcessoJudicial = dossiePrevicendiarioTree.get("numeroProcessoJudicial").asText();

        String cpf = dossiePrevicendiarioTree.get("cpf").asText();

        LocalDate ajuizamento = OffsetDateTime.parse(dossiePrevicendiarioTree.get("ajuizamento").asText()).toLocalDate();

        calculoEntity.setNumeroProcessoJudicial(numeroProcessoJudicial);

        calculoEntity.setNomeParte(nomeParte);

        calculoEntity.setDataAjuizamento(ajuizamento);

        calculoEntity.setCpf(cpf);

        return calculoEntity;

    }


}
