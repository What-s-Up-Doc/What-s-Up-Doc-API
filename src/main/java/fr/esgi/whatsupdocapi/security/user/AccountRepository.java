package fr.esgi.whatsupdocapi.security.user;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    String store(String email, String password, String role);

    int count();

    Account findOneFromEmail(String email);

    List<Account> findAll();

    Optional<Account> findOne(int accountId);

    void deleteOne(int accountId);

    void modify(String email, String password);
}
