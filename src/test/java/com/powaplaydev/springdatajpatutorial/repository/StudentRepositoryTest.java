package com.powaplaydev.springdatajpatutorial.repository;

import com.powaplaydev.springdatajpatutorial.entity.Guardian;
import com.powaplaydev.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// SpringBootTest - Helps test the repository layer. Changes to the database are kept

// @DataJpaTest - Helps test the repository layer. Once a db operation is completed, the data is flushed and
// changes to the database are dropped. Ideally we would use this when testing, but for
// the purpose of this tutorial, we don't use it since we want to have the changes persist in

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("apawel63@gmail.com")
                .firstName("Alex")
                .lastName("Pawelczyk")
                //.guardianName("Jan Pawelczyk")
                //.guardianEmail("jpawel0123@hotmail.com")
                //.guardianMobile("5863370195")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Renata Pawelczyk")
                .email("rpawel0123@gmail.com")
                .mobile("5863376722")
                .build();

        Student student = Student.builder()
                .emailId("apawel64@gmail.com")
                .firstName("Alex")
                .lastName("Pawelczyk")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student list: " + studentList);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> studentList = studentRepository.findByFirstName("Alex");
        System.out.println("Student list: " + studentList.toString());
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> studentList = studentRepository.findByFirstNameContaining("B");
        System.out.println("Student list: " + studentList.toString());
    }

    @Test
    public void printStudentByGuardianName() {
        List<Student> studentList = studentRepository.findByGuardianName("Jan Pawelczyk");
        System.out.println("Student list: " + studentList.toString());
    }


    // Testing custom JPQL query
    @Test
    public void printStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddress("apawel63@gmail.com");
        System.out.println("Student: " + student);

    }

    @Test
    public void printStudentFirstNameByEmailAddress() {
        String studentFirstName = studentRepository.getStudentFirstNameByEmailAddress("apawel63@gmail.com");
        System.out.println("Student: " + studentFirstName);
    }

    @Test
    public void printGetStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailAddressNative("apawel63@gmail.com");
        System.out.println("Student: " + student);
    }

    @Test
    public void getStudentByEmailAddressNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("apawel63@gmail.com");
        System.out.println("Student: " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId("Billy", "apawel63@gmail.com");
    }
}