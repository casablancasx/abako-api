package br.gov.agu.abakoapi.service;


import br.gov.agu.abakoapi.dto.FiltroRequestDTO;
import br.gov.agu.abakoapi.dto.FiltroResponseDTO;
import br.gov.agu.abakoapi.entities.CalculoEntity;
import br.gov.agu.abakoapi.entities.UserEntity;
import br.gov.agu.abakoapi.repositories.CalculoRepository;
import br.gov.agu.abakoapi.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class FiltroService {


    private final UserService userService;
    private final FiltrarBeneficiosTreeService filtrarBeneficiosTreeService;
    private final FiltrarDossiePrevidenciarioTreeService filtrarDossiePrevidenciarioTreeService;

    private final CalculoRepository calculoRepository;

    private final WebClient webClient;


    public FiltroResponseDTO filtrarDossie(FiltroRequestDTO requestDTO) throws JsonProcessingException {

        ObjectMapper mapper = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("processoId", requestDTO.getProcessoId())
                        .queryParam("processoId", requestDTO.getProcessoId())
                        .build()
                )
                .headers(httpHeaders -> httpHeaders.setBearerAuth(requestDTO.getToken()))
                .retrieve()
                .bodyToMono(ObjectMapper.class)
                .block();

        assert mapper != null;
        JsonNode root = mapper.readTree(mapper.writeValueAsString(mapper));
        UserEntity user = userService.validarUsuario(requestDTO);
        CalculoEntity calculo = new CalculoEntity();

        JsonNode dossiePrevidenciarioTree = root.get("DossiePrevidenciario");
        calculo = filtrarDossiePrevidenciarioTreeService.filtrarDossiePrevidenciarioTree(calculo, dossiePrevidenciarioTree);
        calculo.setUser(user);
        calculo = filtrarBeneficiosTreeService.filtrarBeneficiosTree( root.get("beneficios"), calculo);

        calculoRepository.save(calculo);


        FiltroResponseDTO responseDTO = new FiltroResponseDTO();








    }


    private boolean isDossieForaDaValidade(LocalDate dataGeracaoDossie, UserEntity user){
        int diasEntreDataGeracaoEDataAtual = LocalDate.now().compareTo(dataGeracaoDossie);
        int preferenciaDoUsuario = user.getTempoVerificarDossie();
        return diasEntreDataGeracaoEDataAtual > preferenciaDoUsuario;
    }




}
