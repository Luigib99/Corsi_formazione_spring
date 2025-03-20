package com.example.demo.utils;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;

public class UserConverter {

    public static UserDTO entityToDTO (User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNome(user.getNome());
        userDTO.setCognome(user.getCognome());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public static User DTOToEntity (UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setNome(userDTO.getNome());
        user.setCognome(userDTO.getCognome());
        user.setEmail(userDTO.getEmail());
        user.setPassword("");
        return user;
    }
}
