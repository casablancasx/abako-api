package br.gov.agu.abakoapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TipoCorrecaoMonetaria {

    TIPO2("INPC"),
    TIPO4("INPC + SELIC"),
    TIPO6("IPCAE + SELIC");

    private String descricao;


}
