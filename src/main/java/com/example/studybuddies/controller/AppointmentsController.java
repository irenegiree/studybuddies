package com.example.studybuddies.controller;

import com.example.studybuddies.model.Appointment;
import com.example.studybuddies.model.Message;
import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.Tutor;
import com.example.studybuddies.repository.AppointmentRepository;
import com.example.studybuddies.service.AppointmentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.hibernate.boot.jaxb.mapping.Adapter9;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@SessionAttributes({"student", "tutor"})
@Controller
public class AppointmentsController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping(path="/appointment")
    public String appointmentForm(Model model, ModelMap mm,
                                  @RequestParam(name="keyword",defaultValue="")String keyword,
                                  HttpSession session){

        Student student = (Student) session.getAttribute("student");
        Tutor tutor = (Tutor) session.getAttribute("tutor");
        Appointment appointment = new Appointment();
        if (student != null) {   appointment.setStudentID(student.getId());  }
        //  When Tutor model is created, we can use below:
        //   if (tutor != null) { appointment.setTutorID(tutor.getID());
        model.addAttribute("appointment", appointment);
        return "appointment_form";

    }

    @PostMapping(path="/create-appointment")
    public String createAppointment(Appointment appointment, BindingResult result, @RequestParam("apptTime") String apptTimeString) throws Exception {
        System.out.println(apptTimeString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime apptTime = LocalTime.parse(apptTimeString, formatter);
        appointment.setApptTime(Time.valueOf(apptTime));
        System.out.println(apptTimeString);
        System.out.println(appointment.getApptTime());


        if (result.hasErrors()) {
            return "appointment_form";
        }

                appointmentService.createAppointment(appointment);
        return "redirect:/appointment_list";

    }

    @GetMapping("/edit-appointment/{id}")
    public String editAppointment (@PathVariable(value ="id") long id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "redirect:/appointment_list";
    }
    @GetMapping("/delete-appointment/{id}")
    public String deleteAppointment (@PathVariable(value ="id") long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointment_list";
    }

    @GetMapping(path="/appointment_list")
    public String appointmentList (Model model, ModelMap mm,  @RequestParam(name="keyword", defaultValue = "")String keyword,
                                   HttpSession session){
        List<Appointment> appointments;
        appointments = appointmentService.getAllAppointment();

        Appointment appointment = new Appointment();
        appointment.setApptDate(new Date(2023,8,31));
        appointment.setApptTime(new Time(9,0,0));
        appointment.setTutorID(1);
        appointment.setStudentID(2);
        appointment.setDuration(2);
        appointment.setLink("testing");
        appointment.setSubject("Testing");
        appointments.add(appointment);


        model.addAttribute("appointmentList", appointments);

        return "appointment_list";

    }
}
