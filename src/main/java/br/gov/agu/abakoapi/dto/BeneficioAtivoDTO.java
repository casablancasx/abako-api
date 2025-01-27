package br.gov.agu.abakoapi.dto;

import br.gov.agu.abakoapi.enums.BeneficiosEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficioAtivoDTO {

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

    private List<BeneficioCessadoDTO> beneficiosCessados;

}
