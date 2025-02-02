package com.app.users.services;

import com.app.users.dto.AuthenticationRequestDto;
import com.app.users.entity.AdmEntity;
import com.app.users.exception.CustomException;
import com.app.users.repository.AdmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AdmRepository admRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public String authenticate(AuthenticationRequestDto request) {
        AdmEntity admEntity = getAdm(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), admEntity.getPassword())) {
            throw new CustomException("E-mail ou Senha Inválidos!", HttpStatus.UNAUTHORIZED, null);
        }
        return tokenService.generateTokens(admEntity.getId().toString());
    }

    public AdmEntity getAdm(String email) {
        return admRepository.findByEmail(email).orElseThrow(() -> {
            String errorMessage = "Adm não encontrado para o email: " + email;
            System.err.println(errorMessage);
            throw new CustomException("Adm não encontrado!", HttpStatus.NOT_FOUND, null);
        });
    }
}
