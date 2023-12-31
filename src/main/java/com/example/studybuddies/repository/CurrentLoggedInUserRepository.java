package com.example.studybuddies.repository;

import com.example.studybuddies.model.CurrentLoggedInUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentLoggedInUserRepository extends JpaRepository<CurrentLoggedInUser, String>, JpaSpecificationExecutor<CurrentLoggedInUser> {
    @Query("delete from CurrentLoggedInUser clu where clu.email = :email")
    void deleteByEmail(String email);
}
