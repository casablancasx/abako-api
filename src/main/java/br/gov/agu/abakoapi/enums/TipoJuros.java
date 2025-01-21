package br.gov.agu.abakoapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoJuros {

    TIPO2("JUROS + SELIC");

    private String descricao;

}
