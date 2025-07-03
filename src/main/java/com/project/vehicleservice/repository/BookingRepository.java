package com.project.vehicleservice.repository;

import com.project.vehicleservice.model.ServiceBooking;
import com.project.vehicleservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<ServiceBooking, Long> {
    List<ServiceBooking> findByUser(User user);
}
