package fr.esgi.whatsupdocapi.security.user.repository;

import fr.esgi.whatsupdocapi.security.user.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Getter
@RequiredArgsConstructor
public class JdbcAccountRepository implements AccountRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final AccountRowMapper mapper;

    @Override
    public int store(String email, String password, String role) {
        jdbcTemplate.update("INSERT INTO account (email, password, role) " +
                "VALUES (?, ?, ?)", email, password, role);
        return findOneFromEmail(email).getId();
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from account", Integer.class);
    }

    public Account findOneFromEmail(String email) {
        List<Account> accounts = jdbcTemplate.query("select * from account where email = ?", mapper, new Object[]{email});
        if (accounts.isEmpty()) return null;
        return accounts.get(0);
    }

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("select * from account", mapper);
    }

    @Override
    public Optional<Account> findOne(int accountId) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?", mapper, new Object[]{accountId});
        if (accounts.isEmpty()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(accounts.get(0));
    }

    @Override
    public void deleteOne(int accountId) {
        String SQL = "DELETE FROM account WHERE id = ?";
        jdbcTemplate.update(SQL, accountId);
    }

    @Override
    public void modify(int id, String email, String password) {
        String SQL = "Update account set email = ?, password = ? where id = ?";
        jdbcTemplate.update(SQL, email, password, id);
    }

}
