package com.project.vehicleservice.controller;

import com.project.vehicleservice.model.Feedback;
import com.project.vehicleservice.model.User;
import com.project.vehicleservice.services.FeedbackService;
import com.project.vehicleservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @GetMapping("/feedback")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@ModelAttribute("feedback") Feedback feedback,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByEmail(authentication.getName());
            feedback.setUser(user);
            feedbackService.saveFeedback(feedback);
            redirectAttributes.addFlashAttribute("success", "Thank you for your feedback!");
            return "redirect:/feedback?success";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to submit feedback. Please try again.");
            return "redirect:/feedback";
        }
    }
}