package Project.Web.student;

import java.time.LocalDate;

public record StudentRequestDTO(String cpf, String name, String email, String password,
                                String date_of_birth, LocalDate anoIngresso, String classe) {
    public boolean notNullFields(){
        if(cpf == null || email == null || name == null || password == null || date_of_birth == null)
            return false;
        return true;
    }
}
