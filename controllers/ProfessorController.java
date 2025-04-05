package Project.Web.controllers;


import Project.Web.professor.Professor;
import Project.Web.professor.ProfessorRepository;
import Project.Web.professor.ProfessorRequestDTO;
import Project.Web.professor.ProfessorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("professor")
public class ProfessorController {
    @Autowired
    ProfessorRepository repository;

    @GetMapping
    public List<ProfessorResponseDTO> getAllProfessors() {
        List<ProfessorResponseDTO> getAll = repository.findAll().stream().map(ProfessorResponseDTO::new)
                .toList();
        return getAll;
    }
    @PostMapping
    public ResponseEntity addProfessor(@RequestBody ProfessorRequestDTO data){
        if(data.notNullFields()){
            List<ProfessorResponseDTO> getAll = repository.findAll().stream().map(ProfessorResponseDTO::new)
                    .toList();
            boolean hasNoEqualData = false;
            for(int n = 0; n < getAll.size(); n++)
                if(data.email().equals(getAll.get(n).email()) || data.password().equals(getAll.get(n)
                        .password()))
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                         .body("The email or password already exist.");
                else
                    hasNoEqualData = true;
            if(hasNoEqualData) {
                Professor professor = new Professor(data);
                repository.save(professor);
                return ResponseEntity.status(HttpStatus.OK).body("User succesfully registered.");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Some fields from professor entity don't" +
                                                                " accept null values.");
    }
    @PutMapping
    public ResponseEntity professorUpdate(@RequestBody ProfessorRequestDTO dataUpdate){
        List<ProfessorResponseDTO> getAll = repository.findAll().stream().map(ProfessorResponseDTO::new)
                .toList();
        for(int n = 0; n < getAll.size(); n++){
            if(dataUpdate.email().equals(getAll.get(n).email()))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The email informed already exist.");
            if(dataUpdate.password().equals(getAll.get(n).password()))
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                     .body("The password informed already exist.");
        }
        Professor professor = new Professor(dataUpdate);
        repository.save(professor);
        return ResponseEntity.status(HttpStatus.OK).body("Updates succesfully applied.");
    }
}