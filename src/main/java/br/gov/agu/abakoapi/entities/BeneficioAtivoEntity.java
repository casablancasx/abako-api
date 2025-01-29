package br.gov.agu.abakoapi.entities;

import br.gov.agu.abakoapi.enums.BeneficiosEnum;
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

    private LocalDate inicioCalculo;

    private LocalDate fimCalculo;

    private LocalDate inicioJuros;

    private BigDecimal rmi;

    private Integer porcentagemRmi;

    private Integer coeficiente;

    private LocalDate dib;

    private LocalDate dibAnterior;

    @Enumerated(EnumType.STRING)
    private BeneficiosEnum beneficio;

    private String nb;

    @OneToMany(mappedBy = "beneficioAtivo")
    private List<BeneficioCessadoEntity> beneficioCessados;

    @ManyToOne
    @JoinColumn(name = "calculo_id")
    private CalculoEntity calculo;

}
