package Project.Web.student;

import java.time.LocalDate;

public record StudentResponseDTO(String cpf, String name, String email, String password,
                                 LocalDate dateOfBirth, int matricula, LocalDate anoIngresso, String classe) {

    public StudentResponseDTO(Student student){
        this(student.getCpf(), student.getName(), student.getEmail(), student.getPassword(),
                student.getDate_of_birth(), student.getMatricula(), student.getAnoIngresso()
                , student.getClasse());
    }
}
