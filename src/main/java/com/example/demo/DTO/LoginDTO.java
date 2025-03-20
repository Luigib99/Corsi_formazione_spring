package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String nome;
    private String cognome;
    private String email;
    private String password;
}
