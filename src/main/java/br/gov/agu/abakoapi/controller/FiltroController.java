package br.gov.agu.abakoapi.controller;

import br.gov.agu.abakoapi.entities.CalculoEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filtrarProcesso")
public class FiltroController {


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CalculoEntity filtrarProcesso(
            @RequestParam(value = "user_id", required = false) Long userId,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "processo_id", required = false) Long processoId,
            @RequestParam(value = "tarefa_id", required = false) Long tarefaId
    ){




        return new CalculoEntity();
    }
}
