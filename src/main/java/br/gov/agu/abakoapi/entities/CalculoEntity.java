package br.gov.agu.abakoapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "calculo")
    private List<BeneficioAtivoEntity> beneficioAtivos;

    @OneToMany(mappedBy = "calculo")
    private List<BeneficioCessadoEntity> beneficioCessados;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
