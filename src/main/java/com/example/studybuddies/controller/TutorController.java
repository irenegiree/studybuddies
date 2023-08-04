package com.example.studybuddies.controller;

import com.example.studybuddies.model.CurrentLoggedInUser;
import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.Tutor;
import com.example.studybuddies.repository.CurrentLoggedInUserRepository;
import com.example.studybuddies.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TutorController {


        @Autowired
        TutorService tutorService;

        @Autowired
        private CurrentLoggedInUserRepository cluRepo;

        @GetMapping("/registration/tutor")
        public String registerTutor(Model model) {

            Tutor tutor = new Tutor();
            model.addAttribute("tutor", tutor);

            return "tutor_create_form";
        }
        @PostMapping("/create-tutor")
        public String createTutor(@Valid Tutor tutor, BindingResult result) throws Exception{

            if( result.hasErrors()){
                return "tutor_create_form";
            }
            final Tutor tut = tutorService.createTutor(tutor);
            return "redirect:/";
        }

        @PostMapping("/update-tutor")
        public String updateTutor(Tutor tutor) throws Exception{
            final Tutor tut = tutorService.updateTutor(tutor);

            return "redirect:/";
        }

        @GetMapping("/edit-tutor/{id}")
        public String tutorEdit(@PathVariable(value = "id") long id, Model model){

            Tutor tutor = tutorService.getTutorById(id);

            model.addAttribute("tutor",tutor);

            return "tutor_update_form";


        }

        @GetMapping("/detail-tutor/{id}")
        public String showTutorDetail(@PathVariable(value = "id") long id, Model model){

            Tutor tutor = tutorService.getTutorById(id);
            model.addAttribute("tutor", tutor);

            return "tutor_detail";
        }

        @GetMapping("/delete-tutor/{id}")
        public String deleteTutor(@PathVariable(value = "id") long id){

            tutorService.deleteTutor(id);
            return  "redirect:/tutors";
        }

        @GetMapping("/tutor-profile")
        public String tutorProfile(Model model) {

            // get employee from the service
            List<CurrentLoggedInUser> cluList = cluRepo.findAll();
            Tutor tutor = tutorService.getTutorByEmail(cluList.get(0).getEmail());

            // set employee as a model attribute to pre-populate the form
            model.addAttribute("tutor", tutor);
            return "tutor_profile";
        }




    }


