package Project.Web.controllers;

import Project.Web.student.Student;
import Project.Web.student.StudentRepository;
import Project.Web.student.StudentRequestDTO;
import Project.Web.student.StudentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentRepository repository;

    @GetMapping
    public List<StudentResponseDTO> getAll(){
        List<StudentResponseDTO> studentList = repository.findAll().stream().map(StudentResponseDTO::new)
                                                         .toList();
        return studentList;
    }
    @PostMapping
    public ResponseEntity postStudent(@RequestBody StudentRequestDTO data){
        if(data.notNullFields()) {
            Random generator = new Random();
            List<StudentResponseDTO> allStudents = repository.findAll().stream().map(StudentResponseDTO::new)
                                                             .toList();
            int randomValue = 0;
            boolean endTask = true;
            while(endTask) {
                randomValue = generator.nextInt(100000, 999999);
                if (allStudents.size() != 0)
                    for (int n = 0; n < allStudents.size(); n++) {
                        if (allStudents.get(n).matricula() == randomValue);
                        else
                            endTask = false;
                        if(data.email().equals(allStudents.get(n).email())
                                || data.password().equals(allStudents.get(n).password()))
                            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                                 .body("The email or password already exist.");
                    }
                else
                    endTask = false;
            }
            Student student = new Student(data,randomValue);
            repository.save(student);
            return ResponseEntity.status(HttpStatus.OK).body("Student successfully registered.");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Some fields from the student entity " +
                                                                "doesn't accept null values.");
    }
    @PutMapping("/{cpf}")
    public ResponseEntity updateStudent(@PathVariable String cpf, @RequestBody StudentRequestDTO data){
        Optional<Student> targetedStudent = repository.findById(cpf);
        if(targetedStudent.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
        List<StudentResponseDTO> allStudents = repository.findAll().stream().map(StudentResponseDTO::new)
                .toList();
        for (int n = 0; n < allStudents.size(); n++)
            if(!cpf.equals(allStudents.get(n).cpf()))
                if(targetedStudent.get().validateEmailandPassword(data, getAll().get(n).email()
                   , getAll().get(n).password()))
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email/password already exist.");
        targetedStudent.get().updateStudent(data, targetedStudent);
        repository.saveAndFlush(targetedStudent.get());
        return ResponseEntity.status(HttpStatus.OK).body("The users's data was successfully updated.");
    }
    @DeleteMapping
    public ResponseEntity deleteStudent(@PathVariable String cpf){
        Optional<Student> targetedStudent = repository.findById(cpf);
        if(targetedStudent.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Student was not found.");
        repository.delete(targetedStudent.get());
        return ResponseEntity.status(HttpStatus.OK).body("The student was successfully deleted.");
    }
}
