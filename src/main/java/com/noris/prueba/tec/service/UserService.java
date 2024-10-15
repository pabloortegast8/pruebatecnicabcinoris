package com.noris.prueba.tec.service;

import com.noris.prueba.tec.exception.EmailAlreadyRegisteredException;
import com.noris.prueba.tec.model.UserModel;
import com.noris.prueba.tec.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired(required = true)
    private UserRepository userRepository;

    @Value("${user.password.pattern}")
    private String passwordPattern;

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // Genera la clave con algoritmo HS256 que genera la key

    public UserModel createUser(UserModel user) {
        Optional<UserModel> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyRegisteredException("El correo ya está registrado");
        }

        // Validar correo con expresión regular
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!user.getEmail().matches(emailPattern)) {
            throw new IllegalArgumentException("El correo no tiene un formato válido");
        }

        // Validar clave con expresión regular configurada en el properties
        if (!user.getPassword().matches(passwordPattern)) {
            throw new IllegalArgumentException("La clave no cumple con el formato requerido");
        }

        user.setId(UUID.randomUUID());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(generateToken(user));
        user.setActive(true);

        return userRepository.save(user);
    }

    private String generateToken(UserModel user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
    }
}
