package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
}
