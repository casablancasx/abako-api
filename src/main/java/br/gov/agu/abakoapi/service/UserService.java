package br.gov.agu.abakoapi.service;

import br.gov.agu.abakoapi.dto.FiltroRequestDTO;
import br.gov.agu.abakoapi.entities.UserEntity;
import br.gov.agu.abakoapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private static final int TEMPO_PADRAO_VERIFICAR_DOSSIE = 60;

    private final UserRepository userRepository;

    public UserEntity validarUsuario(FiltroRequestDTO request) {

        if (isUsuarioExistente(request)) {
            return userRepository.findById(request.getUserId()).get();
        }

        return salvarUsuario(request);
    }


    private boolean isUsuarioExistente(FiltroRequestDTO request) {
        return userRepository.existsById(request.getUserId());
    }

    private UserEntity salvarUsuario(FiltroRequestDTO request) {
        String nome = request.getNome();
        Long id = request.getUserId();
        return userRepository.save(new UserEntity(id, nome, TEMPO_PADRAO_VERIFICAR_DOSSIE,null));
    }


}
