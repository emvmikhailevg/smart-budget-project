package ru.emelianov.smartbudgetproject.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;

}
