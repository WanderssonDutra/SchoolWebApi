package Project.Web.professor;

import Project.Web.student.Student;
import Project.Web.student.StudentRequestDTO;
import Project.Web.user.User;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity(name="Professor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends User {
    private String especializacao;
    private double salario;

    public Professor(ProfessorRequestDTO data){
        this.cpf = data.cpf();
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.date_of_birth = data.date_of_birth();
        this.endereco = data.endereco();
        this.especializacao = data.especializacao();
        this.salario = data.salario();
    }
    public void updateProfessor(ProfessorRequestDTO data, Optional<Professor> professor){
        this.cpf = professor.get().getCpf();
        if(data.name() == null)
            this.name = professor.get().getName();
        else
            this.name = data.name();
        if(data.email() == null)
            this.email = professor.get().getEmail();
        else
            this.email = data.email();
        if(data.password() == null)
            this.password = professor.get().getPassword();
        else
            this.password = data.password();
        if(data.date_of_birth() == null)
            this.date_of_birth = professor.get().getDate_of_birth();
        else
            this.date_of_birth = data.date_of_birth();
        if(data.especializacao() == null)
            this.especializacao = professor.get().getEspecializacao();
        else
            this.especializacao = data.especializacao();
        if(data.salario() == 0)
            this.salario = professor.get().getSalario();
        else
            this.salario = data.salario();
    }
}
