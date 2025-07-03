package com.project.vehicleservice.controller;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.project.vehicleservice.model.ServiceBooking;
import com.project.vehicleservice.model.User;
import com.project.vehicleservice.services.BookingService;
import com.project.vehicleservice.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    // Show booking form
    @GetMapping("/book-service")
    public String showBookingForm(@RequestParam(required = false) String type, Model model) {
        ServiceBooking booking = new ServiceBooking();
        if (type != null) {
            booking.setServiceType(type);
        }
        model.addAttribute("booking", booking);
        return "book-service";
    }

    // Handle booking and redirect to bill
    @PostMapping("/book-service")
    public String bookService(@ModelAttribute("booking") ServiceBooking booking,
                              Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        booking.setUser(user);

        // Dynamic pricing logic
        switch (booking.getServiceType()) {
            case "Oil Change" -> booking.setPrice(499);
            case "Tyre Replacement" -> booking.setPrice(1200);
            case "Engine Check" -> booking.setPrice(799);
            case "Battery Service" -> booking.setPrice(999);
            case "Car Wash" -> booking.setPrice(400);
            case "Full Service" -> booking.setPrice(3000);
            default -> booking.setPrice(2000);
        }

        ServiceBooking savedBooking = bookingService.bookService(booking);
        return "redirect:/bill/" + savedBooking.getId();
    }

    // View user's bookings
    @GetMapping("/view-status")
    public String viewBookings(Model model, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        List<ServiceBooking> bookings = bookingService.getBookingsByUser(user);
        model.addAttribute("bookings", bookings);
        return "view-status";
    }

    // View bill
    @GetMapping("/bill/{id}")
    public String viewBill(@PathVariable Long id, Model model) {
        ServiceBooking booking = bookingService.getBookingById(id);
        if (booking == null) return "redirect:/dashboard";
        model.addAttribute("booking", booking);
        return "bill";
    }

    // Download bill as PDF
    @GetMapping("/bill/download/{id}")
    public void downloadBill(@PathVariable Long id, HttpServletResponse response) throws IOException {
        ServiceBooking booking = bookingService.getBookingById(id);
        if (booking == null) {
            response.sendRedirect("/dashboard");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=bill_" + id + ".pdf");

        try (OutputStream out = response.getOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Vehicle Service Invoice").setBold().setFontSize(18));
            document.add(new Paragraph("Customer: " + booking.getUser().getFullName()));
            document.add(new Paragraph("Email: " + booking.getUser().getEmail()));
            document.add(new Paragraph("Service Type: " + booking.getServiceType()));
            document.add(new Paragraph("Vehicle Number: " + booking.getVehicleNumber()));
            document.add(new Paragraph("Appointment Date: " + booking.getAppointmentDate()));
            document.add(new Paragraph("Total Price: ₹" + booking.getPrice()));

            document.close();
        }
    }
}
