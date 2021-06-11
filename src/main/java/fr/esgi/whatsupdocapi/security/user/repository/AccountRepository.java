package fr.esgi.whatsupdocapi.security.user.repository;

import fr.esgi.whatsupdocapi.security.user.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    String store(int id, String email, String password, String role);

    int count();

    Account findOneFromEmail(String email);

    List<Account> findAll();

    Optional<Account> findOne(int accountId);

    void deleteOne(int accountId);

    void modify(int id, String email, String password);
}
