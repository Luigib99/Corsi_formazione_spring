package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user_test")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable=false)
    private String password;

}
