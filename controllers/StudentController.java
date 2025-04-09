package Project.Web.controllers;

import Project.Web.services.StudentService;
import Project.Web.student.StudentRequestDTO;
import Project.Web.student.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    List<StudentResponseDTO> getAll(){
        return studentService.getAll();
    }
    @PostMapping
    public ResponseEntity postStudent(@RequestBody StudentRequestDTO data) {
        if (data.notNullFields())
            studentService.registerStudent(data);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Some fields from the student entity " +
                "doesn't accept null values.");
    }
    @PutMapping("/{cpf}")
    public ResponseEntity updateStudent(@PathVariable String cpf, @RequestBody StudentRequestDTO data){
        studentService.updateStudent(cpf, data);
        return ResponseEntity.status(HttpStatus.OK).body("The users's data was successfully updated.");
    }
    @DeleteMapping("/{cpf}")
    public ResponseEntity deleteStudent(@PathVariable String cpf){
        studentService.deleteStudent(cpf);
        return ResponseEntity.status(HttpStatus.OK).body("The student was successfully deleted.");
    }
}