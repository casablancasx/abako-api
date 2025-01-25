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
import java.util.List;

@Entity
@Table(name = "tb_beneficio_ativo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficioAtivoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beneficioAtivoId;

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

    @Enumerated(EnumType.STRING)
    private TipoJuros tipoJuros;

    @Enumerated(EnumType.STRING)
    private TipoCorrecaoMonetaria tipoCorrecao;

    private LocalDate atualizarAte;

    private LocalDate dib;

    private LocalDate dibAnterior;

    @Enumerated(EnumType.STRING)
    private BeneficiosEnum beneficio;

    private String nb;

    @ManyToOne
    @JoinColumn(name = "calculo_id")
    private CalculoEntity calculo;


}
