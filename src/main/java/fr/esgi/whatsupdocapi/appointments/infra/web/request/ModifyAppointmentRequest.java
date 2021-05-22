package fr.esgi.whatsupdocapi.appointments.infra.web.request;

import fr.esgi.whatsupdocapi.core.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModifyAppointmentRequest {
    private Integer id;
    private Integer idDoctor;
    private Integer idPatient;
    private String date;
    private String status;

    public void isValid(){
        if (id == null) {
            throw new BadRequestException("You have to provide an : id");
        }
        if (idDoctor == null) {
            throw new BadRequestException("You have to provide an :  idDoctor");
        }
        if (idPatient == null) {
            throw new BadRequestException("You have to provide an : idPatient");
        }
        if (date == null) {
            throw new BadRequestException("You have to provide a : date");
        }
        if (status == null) {
            throw new BadRequestException("You have to provide a : status");
        }
    }
}
