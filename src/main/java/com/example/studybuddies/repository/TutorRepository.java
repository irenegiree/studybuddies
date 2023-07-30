package com.example.studybuddies.repository;

import com.example.studybuddies.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Long>, JpaSpecificationExecutor<Tutor> {
}
