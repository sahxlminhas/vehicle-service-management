package com.project.vehicleservice.services;

import com.project.vehicleservice.model.ServiceBooking;
import com.project.vehicleservice.model.User;
import java.util.List;

public interface BookingService {
    ServiceBooking bookService(ServiceBooking booking);
    List<ServiceBooking> getBookingsByUser(User user);
    ServiceBooking getBookingById(Long id);

}
