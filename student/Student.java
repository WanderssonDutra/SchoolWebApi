package Project.Web.student;

import Project.Web.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;

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
        this.date_of_birth = LocalDate.parse(data.date_of_birth());
        this.anoIngresso = data.anoIngresso();
        this.classe = data.classe();
    }
    public Student(StudentRequestDTO data, int matricula) {
        this.cpf = data.cpf();
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.date_of_birth = LocalDate.parse(data.date_of_birth());
        this.anoIngresso = data.anoIngresso();
        this.classe = data.classe();
        this.matricula = matricula;
    }
    public void updateStudent(StudentRequestDTO data, Optional<Student> student){
        this.cpf = student.get().getCpf();
        if(data.name() == null)
            this.name = student.get().getName();
        else
            this.name = data.name();
        if(data.email() == null)
            this.email = student.get().getEmail();
        else
            this.email = data.email();
        if(data.password() == null)
            this.password = student.get().getPassword();
        else
            this.password = data.password();
        if(data.date_of_birth() == null)
            this.date_of_birth = student.get().getDate_of_birth();
        else
        this.date_of_birth = LocalDate.parse(data.date_of_birth());
        if(data.anoIngresso() == null)
            this.anoIngresso = student.get().getAnoIngresso();
        else
            this.anoIngresso = data.anoIngresso();
        if(data.classe() == null)
            this.classe = student.get().getClasse();
        else
            this.classe = data.classe();
        this.matricula = student.get().getMatricula();
    }

    public boolean validateEmailandPassword(StudentRequestDTO data, String email, String password){
        if(!(data.email() == null || data.email().isEmpty()))
            if(data.email().equals(email))
                return true;
        if(!(data.password() == null || data.password().isEmpty()))
            if(data.password().equals(password))
                return true;
        return false;
    }
}
