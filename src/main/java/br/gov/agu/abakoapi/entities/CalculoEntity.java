package br.gov.agu.abakoapi.entities;

import br.gov.agu.abakoapi.enums.BeneficiosEnum;
import br.gov.agu.abakoapi.enums.TipoCorrecaoMonetaria;
import br.gov.agu.abakoapi.enums.TipoJuros;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_calculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calculo_id;

    private String numeroProcesso;

    private String nomeParte;

    private LocalDate dataAjuizamento;

    private LocalDate inicioCalculo;

    private LocalDate fimCalculo;

    private LocalDate inicioJuros;

    private BigDecimal rmi;

    private LocalDate honorariosAdvocaticiosAte;

    private int percentualHonorariosAdvocaticios;

    private int acordo;

    private int porcentagemRmi;

    private TipoJuros tipoJuros;

    private TipoCorrecaoMonetaria tipoCorrecao;

    private LocalDate atualizarAte;

    private LocalDate dib;

    private LocalDate dibAnterior;

    private boolean isAlcada;

    private BeneficiosEnum beneficio;

    private String nb;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
