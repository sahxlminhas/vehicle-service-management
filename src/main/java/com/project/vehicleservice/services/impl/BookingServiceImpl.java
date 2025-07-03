package com.project.vehicleservice.services.impl;

import com.project.vehicleservice.model.ServiceBooking;
import com.project.vehicleservice.model.User;
import com.project.vehicleservice.repository.BookingRepository;
import com.project.vehicleservice.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public ServiceBooking bookService(ServiceBooking booking) {
        return bookingRepository.save(booking);
    }
    
    @Override
    public ServiceBooking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<ServiceBooking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }
    
    
}
