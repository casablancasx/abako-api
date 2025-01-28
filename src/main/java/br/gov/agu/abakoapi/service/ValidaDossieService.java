package br.gov.agu.abakoapi.service;

import br.gov.agu.abakoapi.entities.UserEntity;
import br.gov.agu.abakoapi.exceptions.DossieForaDeValidade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidaDossieService {


    public void validaDossie(LocalDate dataGeracaoDossie, UserEntity userEntity) {
        int diasEntreGeracaoDossieAteHoje = LocalDate.now().compareTo(dataGeracaoDossie);
        int prefenciaUsuario = userEntity.getTempoVerificarDossie();

        if (diasEntreGeracaoDossieAteHoje > prefenciaUsuario) {
            throw new DossieForaDeValidade("Este dossie foi gerado no dia %s que ultrapassa o seu limite de  dias");

        }
    }

}
