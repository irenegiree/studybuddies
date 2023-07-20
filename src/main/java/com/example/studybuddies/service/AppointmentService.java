package com.example.studybuddies.service;

import com.example.studybuddies.model.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    List<Appointment> getAllAppointment();

    Appointment createAppointment (Appointment appointment);
    Appointment updateAppointment (Appointment appointment);
    void deleteAppointment (long id);
    Appointment getAppointmentById(long id);

}
