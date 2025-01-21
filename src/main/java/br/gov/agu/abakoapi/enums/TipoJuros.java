package br.gov.agu.abakoapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TipoJuros {

    TIPO2("JUROS + SELIC");

    private String descricao;

}
