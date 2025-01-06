package com.api.oak_store.controller;

import com.api.oak_store.controller.dto.RequestLogin;
import com.api.oak_store.controller.dto.ResponseLogin;
import com.api.oak_store.repository.CostumerRepository;
import com.api.oak_store.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CostumerRepository costumerRepository;
    private final UserService userService;

    public UserController(JwtEncoder jwtEncoder, CostumerRepository costumerRepository,
                          UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.costumerRepository = costumerRepository;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> login(@RequestBody @Valid RequestLogin login) {
        var costumer = costumerRepository.findByEmail(login.email())
                .orElseThrow(() -> new NoSuchElementException("Costumer email dont found"));

        if (userService.isLoginCorrect(login, bCryptPasswordEncoder, costumer)) {
            var expiresIn = 3000L;

            var claims = JwtClaimsSet.builder()
                    .issuer("oak-store")
                    .subject(costumer.getCostumerId().toString())
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plusSeconds(expiresIn))
                    .build();

            var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok(new ResponseLogin(jwtValue, expiresIn));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}