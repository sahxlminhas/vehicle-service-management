package com.project.vehicleservice.repository;

import com.project.vehicleservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //  Marks this as a Spring-managed Repository bean
public interface UserRepository extends JpaRepository<User, Long> {

    //  Custom finder method (Spring will auto-implement this)
    User findByEmail(String email);
}
