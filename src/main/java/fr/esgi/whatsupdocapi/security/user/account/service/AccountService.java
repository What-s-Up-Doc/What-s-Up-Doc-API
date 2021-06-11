package fr.esgi.whatsupdocapi.security.user.account.service;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import fr.esgi.whatsupdocapi.doctors.service.DoctorService;
import fr.esgi.whatsupdocapi.patients.model.Patient;
import fr.esgi.whatsupdocapi.patients.service.PatientService;
import fr.esgi.whatsupdocapi.security.user.account.Account;
import fr.esgi.whatsupdocapi.security.user.account.repository.JdbcAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final DoctorService doctorService;
    private final PatientService patientService;

    private final JdbcAccountRepository accountRepository;


    public int addPatient(String firstname, String lastname, String phone,
                          String gender, String birthday, int smoker, double height,
                          double weight, String medical_history,
                          String family_medical_history, String treatment, int accountId) {
        return patientService.addPatient(firstname, lastname, phone,
                gender, birthday, smoker, height, weight, medical_history,
                family_medical_history, treatment, accountId);
    }

    public int addAccount(String email, String password, String role) {
        return accountRepository.store(email, password, role);
    }

    public Account findAccountByEmail(String email) {
        return accountRepository.findOneFromEmail(email);
    }

    public int addDoctor(String firstname, String lastname, String phone,
                         String gender, String speciality, int accountId) {
        return doctorService.addDoctor(firstname, lastname, phone, gender, speciality, accountId);
    }

    public Optional<Account> findOne(int accountId) {
        return accountRepository.findOne(accountId);
    }

    public void modifyDoctor(int id, String firstname, String lastname,
                             String phone, String gender, String speciality) {

        doctorService.modify(id, firstname, lastname, phone, gender, speciality);
    }

    public void modifyPatient(int id, String firstname, String lastname,
                                 String phone, String gender, String birthday,
                                 int smoker, double height, double weight,
                                 String medical_history, String family_medical_history,
                                 String treatment) {

        patientService.modify( id, firstname, lastname,
                phone, gender, birthday, smoker, height, weight,
                medical_history, family_medical_history, treatment);
    }

    public void modifyAccount(String email, String password) {
        Account account = accountRepository.findOneFromEmail(email);
        accountRepository.modify(account.getId(), email, password);
    }

    public void deleteOnePatient(int accountId) {
        Patient patient = patientService.findPatientFromAccount(accountId);
        patientService.deleteOne(patient.getId());
    }

    public void deleteOneDoctor(int accountId) {
        Doctor doctor = doctorService.findDoctorFromAccount(accountId);
        doctorService.deleteOne(doctor.getId());
    }
}
