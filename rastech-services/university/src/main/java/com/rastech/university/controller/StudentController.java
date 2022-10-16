package com.rastech.university.controller;

import com.rastech.university.domain.Gender;
import com.rastech.university.domain.Student;
import com.rastech.university.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * @author Ali Shiravand, 10/15/22 7:07 PM
 */
@RestController
@RequestMapping("/api/v1/rastech/university/student")
public record StudentController(StudentService studentService) {

    @PostMapping
    public ResponseEntity<Student> store(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.store(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("find-by-email")
    public ResponseEntity<Student> findByEmail(
            @RequestParam(name = "email") String email) {
        try {
            return ResponseEntity.ok(studentService.findByEmail(email));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("find-by-gender")
    public ResponseEntity<List<Student>> findAllByGender(
            @RequestParam(name = "gender") Gender gender) {
        return ResponseEntity.ok(studentService.findAllByGender(gender));
    }
}
