package com.desafios.DesafiosEducativosBackend.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String firstName;
    String lastName;
    String email;
    String username;
    String password;
    Integer gender;
}
