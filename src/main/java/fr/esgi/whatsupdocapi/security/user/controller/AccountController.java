package fr.esgi.whatsupdocapi.security.user.controller;

import fr.esgi.whatsupdocapi.core.exceptions.BadRequestException;
import fr.esgi.whatsupdocapi.security.user.request.CreateAccountDoctorRequest;
import fr.esgi.whatsupdocapi.security.user.request.CreateAccountPatientRequest;
import fr.esgi.whatsupdocapi.security.user.request.ModifyAccountDoctorRequest;
import fr.esgi.whatsupdocapi.security.user.request.ModifyAccountPatientRequest;
import fr.esgi.whatsupdocapi.security.user.response.AccountIdResponse;
import fr.esgi.whatsupdocapi.security.user.service.AccountService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@Data
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/doctors")
    public ResponseEntity<AccountIdResponse> createDoctorAccount(@RequestBody CreateAccountDoctorRequest request) {
        AccountControllerHelper.verifyPasswordValidity(request.getPassword(), request.getConfirmedPassword());
        AccountControllerHelper.verifyUniqueEmailInRepository(accountService.findAccountByEmail(request.getEmail()));

        int accountId = accountService.addAccount(request.getEmail(), passwordEncoder.encode(request.getPassword()), "DOCTOR");

        try {
            accountService.addDoctor(request.getFirstname(), request.getLastname(),
                    request.getPhone(), request.getGender(), request.getSpeciality(), accountId);
        } catch (Exception e) {
            throw new BadRequestException("Illegal arguments for doctor creation");
        }
        AccountIdResponse accountIdResponse = new AccountIdResponse();
        accountIdResponse.setId(accountId);
        return new ResponseEntity<>(accountIdResponse, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<AccountIdResponse> createPatientAccount(@RequestBody CreateAccountPatientRequest request) {
        AccountControllerHelper.verifyPasswordValidity(request.getPassword(), request.getConfirmedPassword());
        AccountControllerHelper.verifyUniqueEmailInRepository(accountService.findAccountByEmail(request.getEmail()));

        int accountId = accountService.addAccount(request.getEmail(), passwordEncoder.encode(request.getPassword()), "PATIENT");
        try {
            accountService.addPatient(request.getFirstname(), request.getLastname(),
                    request.getPhone(), request.getGender(), request.getBirthday(),
                    request.getSmoker(), request.getHeight(), request.getWeight(),
                    request.getMedical_history(), request.getFamily_medical_history(),
                    request.getTreatment(), accountId);
        } catch (Exception e) {
            throw new BadRequestException("Illegal arguments for patient creation");
        }
        AccountIdResponse accountIdResponse = new AccountIdResponse();
        accountIdResponse.setId(accountId);
        return new ResponseEntity<>(accountIdResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAccountPatient(@PathVariable("id") int accountId) {
        accountService.deleteOnePatient(accountId);
    }

    @DeleteMapping(path = "/doctors/{id}")
    public void deleteAccountDoctor(@PathVariable("id") int accountId) {
        accountService.deleteOneDoctor(accountId);
    }

    @PutMapping(path = "/doctor")
    public void modifyDoctor(@RequestBody ModifyAccountDoctorRequest request) {
        try {
            accountService.modifyAccount(request.getEmail(), passwordEncoder.encode(request.getPassword()));
            accountService.modifyDoctor(request.getId(), request.getFirstname(), request.getLastname(),
                    request.getPhone(), request.getGender(), request.getSpeciality());
        } catch (Exception e) {
            throw new BadRequestException("Illegal arguments for doctor modification");
        }
    }

    @PutMapping
    public void modifyPatient(@RequestBody ModifyAccountPatientRequest request) {
        try {
            accountService.modifyAccount(request.getEmail(), passwordEncoder.encode(request.getPassword()));
            accountService.modifyPatient(request.getId(), request.getFirstname(), request.getLastname(),
                    request.getPhone(), request.getGender(),
                    request.getBirthday(), request.getSmoker(), request.getHeight(), request.getWeight(),
                    request.getMedical_history(), request.getFamily_medical_history(), request.getTreatment());
        } catch (Exception e) {
            throw new BadRequestException("Illegal arguments for patient modification");
        }

    }
}
