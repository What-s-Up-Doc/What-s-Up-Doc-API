package fr.esgi.whatsupdocapi.security.user;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    public String store(String email, String password);

    public int count();

    public Account findOneFromEmail(String email);

    public List<Account> findAll();

    public Optional<Account> findOne(int accountId);

    public void deleteOne(int accountId);

    public void modify(String email, String password);

}
