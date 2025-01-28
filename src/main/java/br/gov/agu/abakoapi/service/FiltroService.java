package br.gov.agu.abakoapi.service;


import br.gov.agu.abakoapi.dto.FiltroRequestDTO;
import br.gov.agu.abakoapi.dto.FiltroResponseDTO;
import br.gov.agu.abakoapi.entities.CalculoEntity;
import br.gov.agu.abakoapi.entities.UserEntity;
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

    private final UserRepository userRepository;

    private final UserService userService;
    private final FiltrarDossiePrevidenciarioTreeService filtrarDossiePrevidenciarioTreeService;

    private final WebClient webClient;



    //TODO ISSO AQUI TA MUITO RUIM AINDA
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

        FiltroResponseDTO responseDTO = new FiltroResponseDTO();
        responseDTO.setCalculo(calculo);





    }


    private boolean isDossieForaDaValidade(LocalDate dataGeracaoDossie, UserEntity user){
        int diasEntreDataGeracaoEDataAtual = LocalDate.now().compareTo(dataGeracaoDossie);
        int preferenciaDoUsuario = user.getTempoVerificarDossie();
        return diasEntreDataGeracaoEDataAtual > preferenciaDoUsuario;
    }




}
