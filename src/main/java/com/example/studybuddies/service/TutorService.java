package com.example.studybuddies.service;

import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.Tutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TutorService {

    List<Tutor> getAllTutors();

    List<Tutor> getMatchingTutors();

    Tutor createTutor(Tutor tutor);

    Tutor updateTutor(Tutor tutor);

    Tutor editTutor(Tutor tutor);

    void deleteTutor(long id);

    Tutor getTutorById(long id);

    Tutor getTutorByEmail(String email);
}


