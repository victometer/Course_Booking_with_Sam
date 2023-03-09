package com.example.CourseBooking.controllers;

import com.example.CourseBooking.models.Booking;
import com.example.CourseBooking.models.Course;
import com.example.CourseBooking.models.Customer;
import com.example.CourseBooking.repositories.BookingRepository;
import com.example.CourseBooking.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(
            @RequestParam(name="rating", required = false) Integer input_rating,
            @RequestParam(name="customer", required = false)Customer input_customer
    ){

//        if we get a customer - return all relevant courses
        if ( input_customer != null) {
            //        get bookings with that customer
            List<Booking> list_of_bookings = bookingRepository.findByCustomer(input_customer);
            //        for each booking get the course
            List<Course> list_of_courses = new ArrayList<>();
            for (Booking booking : list_of_bookings) {
                Course found_course = booking.getCourse();
                list_of_courses.add(found_course);
            }
            //        return list of courses
            return new ResponseEntity<>(list_of_courses, HttpStatus.FOUND);
        }

//        if we have a rating - get by rating
        if ( input_rating != null ) {
            return new ResponseEntity(courseRepository.findByRating(input_rating), HttpStatus.FOUND);
        }

//         no params - return all
        return new ResponseEntity(courseRepository.findAll(), HttpStatus.OK);
    }

}
