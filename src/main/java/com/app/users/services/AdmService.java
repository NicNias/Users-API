package com.app.users.services;

import com.app.users.dto.AdmDto;
import com.app.users.entity.AdmEntity;
import com.app.users.exception.CustomException;
import com.app.users.mappers.AdmMapper;
import com.app.users.repository.AdmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmService {
    private final AdmMapper admMapper;
    private final AdmRepository admRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdmDto createAdm(AdmDto admDto) {
        admRepository.findByEmail(admDto.getEmail()).ifPresent(admEntity -> {
            throw new CustomException("Email ja cadastrado no Sistema", HttpStatus.CONFLICT, null);
        });
        String encryptedPassword = passwordEncoder.encode(admDto.getPassword());
        AdmEntity newAdm = admMapper.toModel(admDto);
        newAdm.setPassword(encryptedPassword);
        admRepository.save(newAdm);
        return admMapper.toDto(newAdm);
    }

    public List<AdmDto> findAdmAll() {
        List<AdmEntity> adms = admRepository.findAll();
        if (adms.isEmpty()) {
            throw new CustomException("Nenhum Adm encontrado", HttpStatus.NOT_FOUND, null);
        }
        return admMapper.toDtoList(adms);
    }
}
