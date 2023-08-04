package com.example.studybuddies.service;

import com.example.studybuddies.model.CurrentLoggedInUser;
import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.Tutor;
import com.example.studybuddies.model.User;
import com.example.studybuddies.repository.CurrentLoggedInUserRepository;
import com.example.studybuddies.repository.StudentRepository;
import com.example.studybuddies.repository.TutorRepository;
import com.example.studybuddies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorServicempl implements TutorService  {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentLoggedInUserRepository cluRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Tutor> getMatchingTutors() {
        CurrentLoggedInUser clu = cluRepo.findAll().get(0);
        Student student = studentRepository.findByEmail(clu.getEmail()).get();
        List<Tutor> tutorList = tutorRepository.findMatchingTutors(student.getPreferredLanguage(), student.getSubject1(), student.getSubject2(), student.getSubject3());
        return tutorList;
    }

    @Override
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor createTutor(Tutor tutor) {
        Tutor savedTutor = this.tutorRepository.save(tutor);
        User user = new User((int)savedTutor.getId(), 0, tutor.getEmail(), encoder.encode(tutor.getPassword()), "ROLE_TUTOR");
        userRepository.save(user);
        return savedTutor;
    }

    @Override
    public Tutor updateTutor(Tutor tutor) {
        return this.tutorRepository.save(tutor);
    }

    @Override
    public Tutor editTutor(Tutor tutor) {
        return this.tutorRepository.save(tutor);
    }

    @Override
    public void deleteTutor(long id) {
        this.tutorRepository.deleteById(id);


    }

    @Override
    public Tutor getTutorById(long id) {
        Optional<Tutor> optional = tutorRepository.findById(id);

        Tutor tutor = null;

        if(optional.isPresent()){

            tutor = optional.get();
        }
        else {
            throw new RuntimeException("Tutor not found for id ::" + id );


        }
        return tutor;
    }
    @Override
    public Tutor getTutorByEmail(String email) {
        Optional<Tutor> optional = tutorRepository.findByEmail(email);
        Tutor tutor = null;
        if (optional.isPresent()) {
            tutor = optional.get();
        } else {
            throw new RuntimeException(" Tutor not found for email :: " + email);
        }
        return tutor;
    }
}
