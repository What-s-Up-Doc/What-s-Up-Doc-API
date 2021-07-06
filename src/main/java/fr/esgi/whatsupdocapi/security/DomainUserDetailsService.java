package fr.esgi.whatsupdocapi.security;

import fr.esgi.whatsupdocapi.security.user.Account;
import fr.esgi.whatsupdocapi.security.user.repository.JdbcAccountRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final JdbcAccountRepository userRepository;

    public DomainUserDetailsService(JdbcAccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = userRepository.findOneFromEmail(email);
        if(Objects.isNull(account)){
            throw new AuthenticationServiceException("email " + email + " not found");
        }

        return User.builder()
                .username(email)
                .password(account.getPassword())
                .roles(account.getRole())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
