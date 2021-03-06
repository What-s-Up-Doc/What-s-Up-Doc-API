package fr.esgi.whatsupdocapi.security.user.repository;

import fr.esgi.whatsupdocapi.security.user.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setEmail(rs.getString("email"));
        account.setPassword(rs.getString("password"));
        account.setRole(rs.getString("role"));
        return account;
    }
}
