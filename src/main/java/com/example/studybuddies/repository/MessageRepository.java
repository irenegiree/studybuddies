package com.example.studybuddies.repository;


import com.example.studybuddies.model.Message;
import com.example.studybuddies.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository  extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {
}
