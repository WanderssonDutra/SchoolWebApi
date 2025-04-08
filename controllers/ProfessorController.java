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
import java.util.Optional;
import java.util.ResourceBundle;

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
                return ResponseEntity.status(HttpStatus.OK).body("User successfully registered.");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Some fields from professor entity don't" +
                                                                " accept null values.");
    }
    @PutMapping("/{cpf}")
    public ResponseEntity professorUpdate(@PathVariable String cpf,
                                          @RequestBody ProfessorRequestDTO dataUpdate){
        Optional<Professor> targetedProfessor = repository.findById(cpf);
        if(targetedProfessor.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The professor was not found.");
        List<ProfessorResponseDTO> getAll = repository.findAll().stream().map(ProfessorResponseDTO::new)
                .toList();
        for(int n = 0; n < getAll.size(); n++)
            if(!cpf.equals(getAll.get(n).cpf()))
                if(dataUpdate.email().equals(getAll.get(n).email())
                    || dataUpdate.equals(getAll.get(n).password()))
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The email or password " +
                                                                            "already exist.");
        targetedProfessor.get().updateProfessor(dataUpdate, targetedProfessor);
        repository.saveAndFlush(targetedProfessor.get());
        return ResponseEntity.status(HttpStatus.OK).body("the professor was successfully updated.");
    }
    @DeleteMapping
    public ResponseEntity deleteProfessor(@PathVariable String cpf){
        Optional<Professor> targetedProfessor = repository.findById(cpf);
        if(targetedProfessor.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The professor was not found.");
        repository.delete(targetedProfessor.get());
        return ResponseEntity.status(HttpStatus.OK).body("The professor was successfully deleted.");
    }
}