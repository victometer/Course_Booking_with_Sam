package com.example.CourseBooking.controllers;

import com.example.CourseBooking.models.Booking;
import com.example.CourseBooking.models.Course;
import com.example.CourseBooking.models.Customer;
import com.example.CourseBooking.repositories.BookingRepository;
import com.example.CourseBooking.repositories.CourseRepository;
import com.example.CourseBooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(name="course", required = false) Course input_course,
            @RequestParam(name="town", required = false) String input_town,
            @RequestParam(name="age", required = false) Integer input_age
    ){

        if ( input_course != null && input_town != null) {
//            get bookings by course
            List<Booking> bookings_by_course = bookingRepository.findByCourse(input_course);

//            customers by course
            List<Customer> customers_by_course = new ArrayList<>();
            for (Booking booking : bookings_by_course) {
                customers_by_course.add(booking.getCustomer());
            }

//            customers by town
            List<Customer> customer_by_course_and_town = new ArrayList<>();
            for (Customer customer : customers_by_course){
                if (customer.getTown().equals(input_town))
                customer_by_course_and_town.add(customer);
            }
//            if we're not given an age, return the statement below
                if(input_age == null){

            return new ResponseEntity<>(customer_by_course_and_town, HttpStatus.FOUND);
        } else {
                    List<Customer> customer_by_course_town_over_age = new ArrayList<>();
                    for(Customer customer : customer_by_course_and_town){
                        if(customer.getAge() > input_age) {
                            customer_by_course_town_over_age.add(customer);
                        }
                    }
                    return new ResponseEntity<>(customer_by_course_town_over_age, HttpStatus.OK);
                    }

                }

//        if we receive a course parameter - return each customer on that course
        if ( input_course != null ) {
//        get the bookings on that course
            List<Booking> bookings_on_course = bookingRepository.findByCourse(input_course);
//        get customer from each booking
            List<Customer> customers_on_course = new ArrayList<>();
            for (Booking booking : bookings_on_course) {
                customers_on_course.add(booking.getCustomer());
            };
            return new ResponseEntity<>(customers_on_course, HttpStatus.FOUND);
        }
//        return all
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

}

