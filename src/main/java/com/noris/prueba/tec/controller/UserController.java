package com.noris.prueba.tec.controller;

import com.noris.prueba.tec.model.UserModel;
import com.noris.prueba.tec.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "companies resource")
public class UserController {

    @Autowired(required = true)
    private UserService userService;

    @Operation(summary = "este metodo corresponde a una prueba tecnica que inserta valores de un usuario nuevo")
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserModel user) {
        try {
            UserModel createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"mensaje\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("El controlador está funcionando");
    }

}
