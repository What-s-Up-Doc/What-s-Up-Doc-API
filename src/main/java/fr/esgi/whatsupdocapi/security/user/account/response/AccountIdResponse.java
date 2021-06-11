package fr.esgi.whatsupdocapi.security.user.account.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountIdResponse {
    private int id;
}
