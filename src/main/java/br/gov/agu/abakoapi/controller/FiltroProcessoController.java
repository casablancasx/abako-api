package br.gov.agu.abakoapi.controller;

import br.gov.agu.abakoapi.dto.FiltroRequestDTO;
import br.gov.agu.abakoapi.dto.FiltroResponseDTO;
import br.gov.agu.abakoapi.service.FiltroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filtro")
@RequiredArgsConstructor
public class FiltroProcessoController {

    private final FiltroService filtroService;

    @PostMapping("/dossie")
    @ResponseStatus(HttpStatus.OK)
    public FiltroResponseDTO filtrarDossie(@Valid FiltroRequestDTO requestDTO){
        return filtroService.filtrarDossie(requestDTO);
    }


}
