package fr.esgi.whatsupdocapi.security.user;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Account {
    private int id;
    private String email;
    private String password;
    private String role;
}
