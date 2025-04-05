package Project.Web.professor;

import java.time.LocalDate;

public record ProfessorRequestDTO(String cpf, String email, String name, String password,
                                  LocalDate date_of_birth, String endereco, String especializacao,
                                  double salario) {

    public boolean notNullFields(){
        if(cpf == null || email == null || name == null || password == null || date_of_birth == null)
            return false;
        return true;
    }
}
