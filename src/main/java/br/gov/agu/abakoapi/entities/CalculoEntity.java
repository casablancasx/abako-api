package br.gov.agu.abakoapi.entities;

import br.gov.agu.abakoapi.enums.TipoCorrecaoMonetaria;
import br.gov.agu.abakoapi.enums.TipoJuros;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_calculo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calculoId;

    private String nomeParte;

    private String cpf;

    private String numeroProcessoJudicial;

    private LocalDate dataAjuizamento;

    private LocalDate honorariosAdvocaticiosAte;

    private Integer percentualHonorariosAdvocaticios;

    private Integer acordo;

    private LocalDate atualizarAte;

    @OneToMany(mappedBy = "calculo")
    @JsonBackReference
    private List<BeneficioAtivoEntity> beneficioAtivos;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
