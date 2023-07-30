package com.example.studybuddies.service;

import com.example.studybuddies.model.Tutor;
import com.example.studybuddies.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorServicempl implements TutorService  {

    @Autowired
    private TutorRepository tutorRepository;


    @Override
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor createTutor(Tutor tutor) {
        return this.tutorRepository.save(tutor);
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
}
