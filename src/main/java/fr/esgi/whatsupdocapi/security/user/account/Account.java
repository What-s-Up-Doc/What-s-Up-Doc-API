package fr.esgi.whatsupdocapi.security.user.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Account {
    private int id;
    private String email;
    private String password;
    private String role;
}
