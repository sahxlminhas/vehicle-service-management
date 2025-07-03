package com.project.vehicleservice.repository;

import com.project.vehicleservice.model.Feedback;
import com.project.vehicleservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUser(User user);
}
