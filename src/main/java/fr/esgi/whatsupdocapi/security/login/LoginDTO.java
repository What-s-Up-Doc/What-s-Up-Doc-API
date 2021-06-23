package fr.esgi.whatsupdocapi.security.login;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}