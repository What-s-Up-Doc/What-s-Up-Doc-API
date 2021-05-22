package fr.esgi.whatsupdocapi.appointments.infra.web.adapter;

import fr.esgi.whatsupdocapi.appointments.infra.web.response.AppointmentDetailResponse;
import fr.esgi.whatsupdocapi.appointments.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentAdapter {

    public AppointmentDetailResponse map(Appointment appointment){
        return AppointmentDetailResponse.builder()
                .doctorId(appointment.getId_doctor())
                .patientId(appointment.getId_patient())
                .dateTime(appointment.getDate())
                .status(appointment.getStatus())
                .build();
    }

}
