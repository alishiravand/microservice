package com.rastech.university.service;

import com.rastech.university.domain.Gender;
import com.rastech.university.domain.Student;
import com.rastech.university.repository.NativeStudentRepository;
import com.rastech.university.repository.StudentRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Ali Shiravand, 10/15/22 7:07 PM
 */
@Service
public record StudentService(StudentRepository repository, MongoTemplate mongoTemplate) {
    public Student store(Student student) {
        return repository.save(student);
    }

    //NB: just for Mockito ArgumentCaptor in Student Service Test
    /* public Student store(Student student) {
        Student newStudent = student.clone();
        newStudent.setEmail(student.getEmail() + ".");
        return repository.save(newStudent);
    }*/

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findByEmail(String email) throws Throwable {
        Optional<Student> result = repository.findByEmail(email);
        result.orElseThrow((Supplier<Throwable>) () ->
                new Exception("Student not found with this email: " + email));
        //NB: This save just for unit test on Student test;
        // I want to test verify with never() method call
        Student resultStudent = result.get();
        resultStudent.setCreatedDateTime(LocalDateTime.now());
        repository.save(resultStudent);
        return result.get();
    }

    public List<Student> findAllByGender(Gender gender) {
        NativeStudentRepository nativeStudentRepository =
                new NativeStudentRepository(mongoTemplate);
        return nativeStudentRepository.findAllByGender(gender);
    }
}
