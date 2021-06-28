package fr.esgi.whatsupdocapi.security.login;

import fr.esgi.whatsupdocapi.security.TokenProvider;
import fr.esgi.whatsupdocapi.security.user.Account;
import fr.esgi.whatsupdocapi.security.user.service.AccountService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Data
@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthentificationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManager;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        authenticationManager.getObject().authenticate(authenticationToken);
        Account account = this.accountService.findAccountByEmail(loginDTO.getEmail());
        String token = tokenProvider.createToken(account);
        LoginResponse response = new LoginResponse();
        response.setToken(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
