package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("students")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    // 1. Add student
    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        String result = studentService.addStudent(student);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // 2. Add teacher
    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){
        String result = studentService.addTeacher(teacher);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // 3. Add existing Teacher and student pair
    @PutMapping("/add-student-teacher-pair")
    public ResponseEntity<String> addStudentTeacherPair(@RequestParam("student") String student, @RequestParam("teacher") String teacher){
        String result = studentService.addStudentTeacherPair(student, teacher);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // 4. Get student by name
    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        Student student = studentService.getStudentByName(name); // Assign student by calling service layer method

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // 5. Get teacher by name
    @GetMapping("/get-teacher-by-name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name){
        Teacher teacher = studentService.getTeacherByName(name); // Assign student by calling service layer method
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    // 6. Get students by teacher name
    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public ResponseEntity<List<String>> getStudentsByTeacherName(@PathVariable String teacher){
        List<String> students = studentService.getStudentsByTeacherName(teacher); // Assign list of student by calling service layer method
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    // 7. Get all the students
    @GetMapping("/get-all-students")
    public ResponseEntity<List<String>> getAllStudents(){
        List<String> students = studentService.getAllStudents(); // Assign list of student by calling service layer method
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    // 8. Delete all teacher and names associated with teacher
    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteTeacherByName(@RequestParam("teacher") String teacher){
        String result = studentService.deleteTeacherByName(teacher);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // 9. Delete all databases
    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllTeachers(){
        String result = studentService.deleteAllTeachers();
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
