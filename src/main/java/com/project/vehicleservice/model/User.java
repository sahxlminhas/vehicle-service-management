package com.project.vehicleservice.model;

import jakarta.persistence.*;
import java.util.*;

@Entity  // Marks this class as a JPA entity (maps to a DB table)
@Table(name = "users")  //  Specifies the DB table name
public class User {

    @Id  //  Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //  Auto-increment ID
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role = "USER";  //  Default role (for Spring Security)

    //  Relationships (optional for now, but can be helpful)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ServiceBooking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    //  Constructors
    public User() {
    	
    }

    public User(String fullName, String email, String password, String role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    

    //  Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ServiceBooking> getBookings() {
		return bookings;
	}

	public void setBookings(List<ServiceBooking> bookings) {
		this.bookings = bookings;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	
    
}
