package br.gov.agu.abakoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiosDTO {

        private String nome;
        private String descricao;
        private String valor;
        private String dataInicio;
        private String dataFim;
        private String tipo;
        private String status;
        private String id;
}
