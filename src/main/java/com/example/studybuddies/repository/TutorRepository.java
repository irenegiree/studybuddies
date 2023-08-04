package com.example.studybuddies.repository;

import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Long>, JpaSpecificationExecutor<Tutor> {
    @Query("select distinct t from Tutor t where t.preferredLanguage = :preferredLanguage " +
            "OR (t.subject1 != '' AND t.subject1 = :sub1 ) OR (t.subject1 != '' AND t.subject1 = :sub2 ) " +
            "OR (t.subject1 != '' AND t.subject1 = :sub3 ) OR (t.subject2 != '' AND t.subject2 = :sub1 ) " +
            "OR (t.subject2 != '' AND t.subject2 = :sub2 ) OR (t.subject2 != '' AND t.subject2 = :sub3 ) " +
            "OR (t.subject3 != '' AND t.subject3 = :sub1 ) OR (t.subject3 != '' AND t.subject3 = :sub2 ) OR (t.subject3 != '' AND t.subject3 = :sub3 )")
    List<Tutor> findMatchingTutors(String preferredLanguage, String sub1, String sub2, String sub3);

    Optional<Tutor> findByEmail(String email);
}
