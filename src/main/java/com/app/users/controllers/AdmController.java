package com.app.users.controllers;

import com.app.users.dto.AdmDto;
import com.app.users.dto.AuthenticationRequestDto;
import com.app.users.dto.SessionDto;
import com.app.users.services.AdmService;
import com.app.users.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adm")
public class AdmController {
    private final AdmService admService;
    private final AuthenticationService authenticationService;

    @PostMapping("/create")
    public ResponseEntity<AdmDto> saveAdm(@RequestBody @Valid AdmDto admDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(admService.createAdm(admDto));
    }

    @PostMapping("/login")
    public SessionDto login(@RequestBody AuthenticationRequestDto request) {
        String token = authenticationService.authenticate(request);
        return new SessionDto(token);
    }

    @PostMapping("/findall")
    public ResponseEntity<List<AdmDto>> getAllAdms() {
        return ResponseEntity.status(HttpStatus.OK).body(admService.findAdmAll());
    }
}
