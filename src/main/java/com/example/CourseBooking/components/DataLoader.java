package com.example.CourseBooking.components;

import com.example.CourseBooking.models.Booking;
import com.example.CourseBooking.models.Course;
import com.example.CourseBooking.models.Customer;
import com.example.CourseBooking.repositories.BookingRepository;
import com.example.CourseBooking.repositories.CourseRepository;
import com.example.CourseBooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BookingRepository bookingRepository;

    public DataLoader(){}


        public void run(ApplicationArguments args){

        Customer customer1 = new Customer("Davie", "Edinburgh", 27);
        customerRepository.save(customer1);
        Customer customer2 = new Customer("Jane", "Stirling", 23);
        customerRepository.save(customer2);

        Course course1 = new Course("Intro to Python", "CodeClan", 5);
        courseRepository.save(course1);
        Course course2 = new Course("Black Smithing for beginners", "Anshtruter", 3);
        courseRepository.save(course2);

        Booking booking1 = new Booking("12-12-2023", course1, customer1);
        bookingRepository.save(booking1);
        Booking booking2 = new Booking("12-12-2023", course1, customer2);
        bookingRepository.save(booking2);
        Booking booking3 = new Booking("12-11-2023", course2, customer2);
        bookingRepository.save(booking3);



    }
}
