package com.stackroute.enrollment.controller;

import com.stackroute.enrollment.component.RabbitProducer;
import com.stackroute.enrollment.domain.Enrollment;
import com.stackroute.enrollment.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EnrollmentController
{

    private EnrollmentService enrollmentService;
    private RabbitProducer rabbitProducer;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, RabbitProducer rabbitProducer) {
        this.enrollmentService = enrollmentService;
        this.rabbitProducer=rabbitProducer;
    }

    @PostMapping("/enrollment")
    public ResponseEntity<?> saveEnrollment(@RequestBody Enrollment enrollment) {
      Enrollment  enrollment1= enrollmentService.saveEnrollment(enrollment);
        rabbitProducer.produce(enrollment1);
        return new ResponseEntity<String>("succefullly created", HttpStatus.CREATED);
    }

    // this method will get all Enrollments
    @GetMapping("enrollments")
    public ResponseEntity<List<Enrollment>> getALlEnrollments() {
        List<Enrollment> list = enrollmentService.getALLRest();
        return new ResponseEntity<List<Enrollment>>(list,HttpStatus.OK);
    }
    // this method will get Enrollment by id
    @GetMapping("enrollment/{id}")
    public ResponseEntity<?> getEnrollmentById(@PathVariable String id) {
        Enrollment enrollment= enrollmentService.findbyId(id);
        return new ResponseEntity<Enrollment>(enrollment,HttpStatus.OK);
    }
    @DeleteMapping("enrollment/{id}")
    public  ResponseEntity<?> deleteEnrollment(@PathVariable String id)
    {
        enrollmentService.delete(id);
        return new ResponseEntity<String>("succefullly deleted", HttpStatus.OK);
    }
}
