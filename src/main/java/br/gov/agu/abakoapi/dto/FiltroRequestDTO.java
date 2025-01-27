package br.gov.agu.abakoapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroRequestDTO {

    @NotNull(message = "O campo userId é obrigatório")
    private Long userId;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo token é obrigatório")
    private String token;

    @NotNull(message = "O campo tarefaId é obrigatório")
    private Long tarefaId;

    @NotNull(message = "O campo processoId é obrigatório")
    private Long processoId;
}
