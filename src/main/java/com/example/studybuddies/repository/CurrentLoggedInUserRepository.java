package com.example.studybuddies.repository;

import com.example.studybuddies.model.CurrentLoggedInUser;
import com.example.studybuddies.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentLoggedInUserRepository extends JpaRepository<CurrentLoggedInUser, String>, JpaSpecificationExecutor<CurrentLoggedInUser> {
}
