package com.noris.prueba.tec.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String email;
    private String password;

    @ElementCollection  // Define la colección embebida
    @CollectionTable(name = "user_phones", joinColumns = @JoinColumn(name = "user_id"))  // Define la tabla para la colección embebida
    private List<PhoneModel> phones;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
}
