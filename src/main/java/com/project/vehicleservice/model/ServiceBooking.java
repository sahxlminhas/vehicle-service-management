package com.project.vehicleservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_bookings")
public class ServiceBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceType;
    private String vehicleNumber;
    private String appointmentDate;

    private int price; // ✅ Price column for service

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // foreign key to User table
    private User user;

    // ===== Constructors =====
    public ServiceBooking() {}

    public ServiceBooking(String serviceType, String vehicleNumber, String appointmentDate, User user, int price) {
        this.serviceType = serviceType;
        this.vehicleNumber = vehicleNumber;
        this.appointmentDate = appointmentDate;
        this.user = user;
        this.price = price;
    }

    // ===== Getters and Setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
