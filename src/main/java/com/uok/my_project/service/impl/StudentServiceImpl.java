package com.uok.my_project.service.impl;

import com.uok.my_project.model.Student;
import com.uok.my_project.repository.StudentRepository;
import com.uok.my_project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepsitory;

    // save student in database
    @Override
    public Student saveStudent(Student student) {
        return studentRepsitory.save(student);
    }

    // get all student form database
    @Override
    public List<Student> getAllStudent() {
        return studentRepsitory.findAll();
    }

    // get student using id
    @Override
    public Student getStudentById(long id) {
        Optional<Student> student = studentRepsitory.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new RuntimeException();
        }
    }

    // update student
    @Override
    public Student updateStudent(Student student, long id) {
        Student existingStudent = studentRepsitory.findById(id).orElseThrow(
                () -> new RuntimeException());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        // save
        studentRepsitory.save(existingStudent);
        return existingStudent;
    }

    // delete student
    @Override
    public void deleteStudent(long id) {
        // check
        studentRepsitory.findById(id).orElseThrow(() -> new RuntimeException());
        // delete
        studentRepsitory.deleteById(id);
    }
}
