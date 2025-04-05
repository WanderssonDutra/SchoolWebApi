package Project.Web.professor;

import java.time.LocalDate;

public record ProfessorResponseDTO(String cpf, String name, String email, String password,
                                   LocalDate date_of_birth) {
    public ProfessorResponseDTO(Professor professor){
        this(professor.getCpf(), professor.getName(), professor.getEmail(), professor.getPassword(),
                professor.getDate_of_birth());
    }
}
