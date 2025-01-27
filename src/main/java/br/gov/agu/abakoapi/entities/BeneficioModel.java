package br.gov.agu.abakoapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "tb_beneficio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficioModel {

    @Id
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "tb_beneficio_inacumulavel",
            joinColumns = @JoinColumn(name = "beneficio_id"),
            inverseJoinColumns = @JoinColumn(name = "inacumulavel_id"))
    @JsonManagedReference
    private List<BeneficioInacumulavelModel> beneficiosInacumulaveis;
}
