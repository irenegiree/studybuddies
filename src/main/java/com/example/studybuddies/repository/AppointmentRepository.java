package com.example.studybuddies.repository;

import com.example.studybuddies.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository  extends JpaRepository <Appointment, Long>, JpaSpecificationExecutor <Appointment> {



}