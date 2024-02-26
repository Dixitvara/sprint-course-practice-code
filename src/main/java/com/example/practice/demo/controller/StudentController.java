package com.example.practice.demo.controller;

import com.example.practice.demo.model.StudentModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // single student api for http://localhost:8080/student
    @GetMapping("/student")
    public StudentModel getStudent()
    {
        StudentModel student = new StudentModel(
            1, "Dixit", "MCA"
        );
        return student;
    }

    // Spring BOOT rest API http request ResponseEntity
    @GetMapping("/studentResponseEntity")
    public ResponseEntity<StudentModel> getStudentEntityResponse()
    {
        StudentModel student = new StudentModel(
                1, "Dixit", "MCA"
        );
//         both way are good for returning method response
//        return new ResponseEntity<>(student, HttpStatus.OK);
//        return ResponseEntity.ok(student);
//     custom header for response
        return ResponseEntity.ok().header("custom-header", "dixit").body(student);
    }

    // list of student api for http://localhost:8080/students
    @GetMapping("/students")
    public List<StudentModel> getStudents()
    {
        List <StudentModel> student = new ArrayList<>();

        student.add(new StudentModel(1, "Dixit", "MCA"));
        student.add(new StudentModel(2, "Siddhesh", "BE"));
        student.add(new StudentModel(3, "Karan", "MCA"));
        student.add(new StudentModel(4, "Ashish", "MCA"));
        student.add(new StudentModel(5, "Chirag", "BCA"));

        return student;
    }

    // Spring REST API with path variable
    // {id} - URI template variable
    // http://localhost:8080/students/{id}/{name}/{course}
    @GetMapping("/students/{id}/{name}/{course}")
    public StudentModel studentPathVariable(@PathVariable int id, @PathVariable String name, @PathVariable String course)
    {
        return new StudentModel(id, name, course);
    }

    // Spring BOOT REST API with request param
    // http://8080/students?id=1&name=raju&course=BCOM - query parameter
    @GetMapping("/students/query")
    public StudentModel studentRequestParam(@RequestParam int id, @RequestParam String name, @RequestParam String course)
    {
        return new StudentModel(id, name, course);
    }

    // Spring BOOT REST API that create post request
    // @PostMapping and @RequestBody
    @PostMapping("/students/create")
    public StudentModel createStudent(@RequestBody StudentModel student)
    {
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getCourse());
        return student;
    }

    // Spring BOOT REST API that update request - update user details using PUT Mapping
    // @PutMapping and @RequestBody
    @PutMapping("/students/{id}/update")
    public StudentModel updateStudent(@RequestBody StudentModel student, @PathVariable int id)
    {
        System.out.println(student.getName());
        System.out.println(student.getCourse());
        return student;
    }

    // Spring BOOT REST API that deletes http delete requests
    // @DeleteMapping
    @DeleteMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable int id)
    {
        return "student deleted at id " + id + "!";
    }

}
