package com.desafios.DesafiosEducativosBackend.domain.DTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;
    String refreshToken;
    String username;
    String firstName;
    String lastName;
    Integer gender;
    Integer id;
    String type;

}
