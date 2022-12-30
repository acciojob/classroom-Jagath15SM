package com.driver;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    HashMap<String, Student> studentDB = new HashMap<>();
    HashMap<String, Teacher> teacherDB = new HashMap<>();
    HashMap<String, List<String>> teacher_students = new HashMap<>();

    public String addStudent(Student student){
        String name = student.getName();
        studentDB.put(name, student);
        return "New student added successfully";
    }


    public String addTeacher(Teacher teacher){
        String name = teacher.getName();
        teacherDB.put(name, teacher);
        if(teacher_students.get(name) == null){
            teacher_students.put(name, null);
        }
        return "New teacher added successfully";
    }


    public String addStudentTeacherPair(String student,String teacher){
        if(teacher_students.get(teacher) == null){
            teacher_students.put(teacher, new ArrayList<>());
        }
        teacher_students.get(teacher).add(student);
        return "New student-teacher pair added successfully";
    }


    public Student getStudentByName(String name){
        if(!studentDB.containsKey(name)) return null;
        Student student = studentDB.get(name);
        return student;
    }


    public Teacher getTeacherByName(String name){
        if(!teacherDB.containsKey(name)) return null;
        Teacher teacher = teacherDB.get(name);
        return teacher;
    }


    public List<String> getStudentsByTeacherName(String teacher){
        List<String> students = teacher_students.get(teacher);
        if(students == null) return null;
        return students;
    }


    public List<String> getAllStudents(){
        List<String> students = new ArrayList<>(studentDB.keySet());
        if(students == null) return null;
        return students;
    }


    public String deleteTeacherByName(String teacher){
        if(teacher_students.containsKey(teacher)){
            if(teacher_students.get(teacher) != null){
                for(String name: teacher_students.get(teacher)){
                    if(studentDB.containsKey(name)){
                        studentDB.remove(name);
                    }
                }
                teacher_students.remove(teacher);
                teacherDB.remove(teacher);
            }
        }
        return teacher + " removed successfully";
    }


    public String deleteAllTeachers(){
        studentDB.clear();
        teacherDB.clear();
        teacher_students.clear();
        return "All teachers deleted successfully";
    }

}
