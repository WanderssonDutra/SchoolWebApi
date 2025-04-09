package Project.Web.services;

import Project.Web.Exceptions.DataBaseConflictException;
import Project.Web.student.Student;
import Project.Web.student.StudentRepository;
import Project.Web.student.StudentRequestDTO;
import Project.Web.student.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public List<StudentResponseDTO> getAll(){
        List<StudentResponseDTO> getAll = repository.findAll().stream()
                                                    .map(StudentResponseDTO::new).toList();
        return getAll;
    }
    public void registerStudent(StudentRequestDTO data){
        emailOrPasswordAlreadyExists(data.cpf(), data);
        Random generator = new Random();
        int randomValue = 0;
        while(true) {
            randomValue = generator.nextInt(100000, 999999);
            if (repository.existsByMatriculaAndCpfNot(randomValue, data.cpf()))
                continue;
            break;
        }
        Student student = new Student(data, randomValue);
        repository.save(student);
    }
    public void updateStudent(String cpf, StudentRequestDTO data){
        Student targetedStudent = repository.findById(cpf)
                                            .orElseThrow(()-> new RuntimeException("Student no found."));
        emailOrPasswordAlreadyExists(cpf, data);
        targetedStudent.updateStudent(data);
        repository.saveAndFlush(targetedStudent);
    }
    public void deleteStudent(String cpf){
        Student targetedStudent = repository.findById(cpf)
                                            .orElseThrow(()-> new RuntimeException("Student not found"));
        repository.delete(targetedStudent);
    }

    public void emailOrPasswordAlreadyExists(String cpf, StudentRequestDTO data){
        if(repository.existsByEmailAndCpfNot(data.email(), cpf))
            throw new DataBaseConflictException("the email is already in use.");
        if(repository.existsByPasswordAndCpfNot(data.password(), cpf))
            throw new DataBaseConflictException("the password is already in use.");
    }
}
