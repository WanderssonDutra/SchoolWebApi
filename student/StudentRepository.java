package Project.Web.student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {

    List<Student> findByEmail(String email);

    List<Student> findByName(String name);

    boolean existsByEmailAndCpfNot(String email, String cpf);

    boolean existsByPasswordAndCpfNot(String password, String cpf);

    boolean existsByMatriculaAndCpfNot(int matricula, String cpf);
}
