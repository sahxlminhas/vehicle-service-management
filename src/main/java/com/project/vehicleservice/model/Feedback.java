package com.project.vehicleservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key
    private User user;

    // Constructors
    public Feedback() {}

    public Feedback(String comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { 
    	return id; 
    	}

    public String getComment() { 
    	return comment; 
    	}
    
    public void setComment(String comment) { 
    	this.comment = comment; 
    	}

    public User getUser() { 
    	return user; 
    	}
    
    public void setUser(User user) { 
    	this.user = user; 
    	}
}
