package fr.esgi.whatsupdocapi.security.user.controller;

import fr.esgi.whatsupdocapi.core.exceptions.BadRequestException;
import fr.esgi.whatsupdocapi.core.exceptions.ConflictException;
import fr.esgi.whatsupdocapi.security.user.Account;

import java.util.Objects;

public class AccountControllerHelper {

    public static void verifyPasswordValidity(String password, String confirmedPassword) throws BadRequestException {
        if(!password.equals(confirmedPassword)){
            throw new BadRequestException("The confirmed password doesn't match the password.");
        }
    }

    public static void verifyUniqueEmailInRepository(Account account) throws ConflictException {
        if(Objects.nonNull(account)){
            throw new ConflictException("A user with this email address has already been created.");
        }
    }
}
