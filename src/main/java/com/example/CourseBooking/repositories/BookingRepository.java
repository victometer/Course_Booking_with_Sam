package com.example.CourseBooking.repositories;

import com.example.CourseBooking.models.Booking;
import com.example.CourseBooking.models.Course;
import com.example.CourseBooking.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCourse(Course input_course);
    List<Booking> findByCustomer(Customer input_customer);

    List<Booking> findByDate(String date);
}
