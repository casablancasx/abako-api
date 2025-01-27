package br.gov.agu.abakoapi.dto;

import br.gov.agu.abakoapi.enums.BeneficiosEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficioCessadoDTO {

    private String nb;

    private BeneficiosEnum especie;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    private BigDecimal rmi;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dibAnterior;

    private int coeficiente;

}
