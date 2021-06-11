package fr.esgi.whatsupdocapi.security.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Account {
    private String email;
    private String password;
    private List<String> roles;
}
