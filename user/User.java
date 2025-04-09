package Project.Web.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@EqualsAndHashCode(of = "cpf")
public class User {
    @Id
    protected String cpf;
    protected String name;
    protected String email;
    protected String password;
    protected LocalDate date_of_birth;
    protected String endereco;
}
