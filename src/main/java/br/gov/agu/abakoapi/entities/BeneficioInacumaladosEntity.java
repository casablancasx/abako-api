package br.gov.agu.abakoapi.entities;

import br.gov.agu.abakoapi.enums.BeneficiosEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_beneficio_inacumalados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficioInacumaladosEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long beneficioInacumaladosId;

    @Enumerated(EnumType.STRING)
    private BeneficiosEnum beneficio;

    private LocalDate inicioDesconto;

    private LocalDate fimDesconto;

    private BigDecimal rmi;

    private int porcentagemRmi;

    private String nb;

    private LocalDate dib;

    private Long dibAnterior;

    @ManyToOne
    @JoinColumn(name = "calculo_id")
    private CalculoEntity calculo;

}
