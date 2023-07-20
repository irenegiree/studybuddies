package com.example.studybuddies.service;

import com.example.studybuddies.model.Appointment;
import com.example.studybuddies.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServicempl implements AppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(long id) {
        this.appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment getAppointmentById(long id) {
        Optional<Appointment> optional = appointmentRepository.findById(id);
        Appointment appointment = null;
        if (optional.isPresent()){
            appointment = optional.get();
        }else {
            throw new RuntimeException("Appointment not found for id :: "+id);
        }

        return appointment;


    }
}
