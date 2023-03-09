package com.example.CourseBooking.controllers;

import com.example.CourseBooking.models.Booking;
import com.example.CourseBooking.models.Course;
import com.example.CourseBooking.repositories.BookingRepository;
import com.example.CourseBooking.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {
    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllCourses(@RequestParam(name="date", required = false) String input_date){

        if ( input_date != null ) {
            return new ResponseEntity<>(bookingRepository.findByDate(input_date), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }

}

