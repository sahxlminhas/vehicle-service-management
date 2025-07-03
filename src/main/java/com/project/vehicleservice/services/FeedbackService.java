package com.project.vehicleservice.services;

import com.project.vehicleservice.model.Feedback;
import com.project.vehicleservice.model.User;
import java.util.List;

public interface FeedbackService {
    Feedback saveFeedback(Feedback feedback);
    List<Feedback> getFeedbacksByUser(User user);
}
