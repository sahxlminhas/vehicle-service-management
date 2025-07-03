package com.project.vehicleservice.services.impl;

import com.project.vehicleservice.model.Feedback;
import com.project.vehicleservice.model.User;
import com.project.vehicleservice.repository.FeedbackRepository;
import com.project.vehicleservice.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getFeedbacksByUser(User user) {
        return feedbackRepository.findByUser(user);
    }
}
