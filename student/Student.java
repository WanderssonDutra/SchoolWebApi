package Project.Web.student;

import Project.Web.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name="Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {

    private int matricula;
    private LocalDate anoIngresso;
    private String classe;

    public Student(StudentRequestDTO data) {
        this.cpf = data.cpf();
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.date_of_birth = data.date_of_birth();
        this.anoIngresso = data.anoIngresso();
        this.classe = data.classe();
    }
    public Student(StudentRequestDTO data, int matricula) {
        this.cpf = data.cpf();
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.date_of_birth = data.date_of_birth();
        this.anoIngresso = data.anoIngresso();
        this.classe = data.classe();
        this.matricula = matricula;
    }

    public void updateStudent(StudentRequestDTO data){
        if(data.name() != null)
            this.name = data.name();
        if(data.email() != null)
            this.email = data.email();
        if(data.password() != null)
            this.password = data.password();
        if(data.date_of_birth() != null)
            this.date_of_birth = data.date_of_birth();
        if(data.anoIngresso() != null)
            this.anoIngresso = data.anoIngresso();
        if(data.classe() != null)
            this.classe = data.classe();
    }
}
