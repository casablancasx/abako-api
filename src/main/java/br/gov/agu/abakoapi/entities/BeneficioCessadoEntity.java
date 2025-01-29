package br.gov.agu.abakoapi.entities;

import br.gov.agu.abakoapi.enums.BeneficiosEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_beneficio_cessado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficioCessadoEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long beneficioCessadoId;

    @Enumerated(EnumType.STRING)
    private BeneficiosEnum beneficio;

    private LocalDate inicioDesconto;

    private LocalDate fimDesconto;

    private BigDecimal rmi;

    private Integer porcentagemRmi;

    private String nb;

    private LocalDate dib;

    private LocalDate dibAnterior;

    @ManyToOne
    @JoinColumn(name = "beneficio_ativo_id")
    private BeneficioAtivoEntity beneficioAtivo;

    @ManyToOne
    @JoinColumn(name = "calculo_id")
    private CalculoEntity calculo;

}
