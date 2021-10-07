package ftg.wesit.digniti.digniti.GestionUtilisateur.Dao;

import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DaoUtilisateur extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByUsernameAndStatusIsTrue(String username);
    List<Utilisateur> findAllByStatusIsTrue();
    @Query("SELECT u FROM Utilisateur u WHERE u.status=true AND u.email LIKE %?1%")
    List<Utilisateur> findAllemail(String email);
}
