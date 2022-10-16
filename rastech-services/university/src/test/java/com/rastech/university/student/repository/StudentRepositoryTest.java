package com.rastech.university.student.repository;

import com.rastech.university.domain.Address;
import com.rastech.university.domain.Gender;
import com.rastech.university.domain.Student;
import com.rastech.university.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Ali Shiravand, 10/16/22 12:18 PM
 */
@SpringBootTest
//@DirtiesContext
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        studentRepositoryUnderTest.deleteAll();
    }

    @Test
    void itShouldFindStudentByEmailIfExist() {
        //given
        String email = "ariamehr.k@ash.com";
        Student student = new Student(
                "Kourosh", "Ariamehr", email, Gender.MALE,
                new Address("Iran", "Tehran", "16679812379"),
                List.of("It", "Reading Book"),
                new BigDecimal(10)
        );
        studentRepositoryUnderTest.save(student);

        //when
        Optional<Student> optionalStudent = studentRepositoryUnderTest.findByEmail(email);

        //then
        assertThat(optionalStudent.isPresent()).isTrue();
    }

    @Test
    void itShouldCheckStudentByEmailIfDoesNotExist() {
        //given
        String email = "ariamehr.k@ash.com";

        //when
        Optional<Student> optionalStudent = studentRepositoryUnderTest.findByEmail(email);

        //then
        assertThat(optionalStudent.isPresent()).isFalse();
    }
}