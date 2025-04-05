package Project.Web.professor;

import Project.Web.user.User;
import jakarta.persistence.Entity;
import lombok.*;

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
}
