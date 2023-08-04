package com.example.studybuddies.controller;

import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.Tutor;
import com.example.studybuddies.repository.StudentRepository;
import com.example.studybuddies.service.StudentService;
import com.example.studybuddies.service.TutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@WebMvcTest(AdminController.class)
class AdminControllerTest {

    Student student;
    Tutor tutor;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @MockBean
    TutorService tutorService;

    @Mock
    View mockView;

    @InjectMocks
    AdminController adminController;

    @BeforeEach
    void setup() throws ParseException {
        Student s2 = new Student();
        s2.setId(200);
        s2.setFirstName("Adam");
        s2.setLastName("Mills");
        s2.setEmail("adammills@yahoo.com");
        s2.setDateOfBirth("1994-08-07");
        s2.setPassword("abcdef123");
        s2.setSchool("University of British Columbia");
        s2.setPhone("7785489485");
        s2.setSubject1("Software Development");
        s2.setExp1("4");
        s2.setSubject2("Database");
        s2.setExp2("7");
        s2.setSubject3("Data Visualization");
        s2.setExp3("2");

        Tutor t2 = new Tutor();
        t2.setId(300);
        t2.setFirstName("Cindy");
        t2.setLastName("Lauper");
        t2.setEmail("cindylauper@yahoo.com");
        t2.setDateOfBirth("1991-08-07");
        t2.setPassword("abcdef123");
        t2.setPhone("2365489485");
        t2.setSubject1("Software Development");
        t2.setExp1("8");
        t2.setSubject2("Database");
        t2.setExp2("11");
        t2.setSubject3("Data Visualization");
        t2.setExp3("5");

        MockitoAnnotations.openMocks(this);

        mockMvc = standaloneSetup(adminController).setSingleView(mockView).build();

    }

    @Test
    void showStudentList() throws Exception {

        List<Student> list = new ArrayList<Student>();
        list.add(student);
        list.add(student);

        when(studentService.getAllStudents()).thenReturn(list);
        mockMvc.perform(get("/admin/students"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("studentList", list))
                .andExpect(view().name("student_list"))
                .andExpect(model().attribute("studentList", hasSize(2)));

        verify(studentService, times(1)).getAllStudents();
        verifyNoMoreInteractions(studentService);
    }


    @Test
    void showTutorList() throws Exception {
        List<Tutor> list = new ArrayList<Tutor>();
        list.add(tutor);
        list.add(tutor);

        when(tutorService.getAllTutors()).thenReturn(list);
        mockMvc.perform(get("/admin/tutors"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tutorList", list))
                .andExpect(view().name("tutor_list"))
                .andExpect(model().attribute("tutorList", hasSize(2)));

        verify(tutorService, times(1)).getAllTutors();
        verifyNoMoreInteractions(tutorService);
    }
}