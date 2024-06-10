package ru.emelianov.smartbudgetproject.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    String username;
    String email;
    String password;
    String confirmPassword;

}
