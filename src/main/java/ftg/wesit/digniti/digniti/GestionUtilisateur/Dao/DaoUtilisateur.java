package ftg.wesit.digniti.digniti.GestionUtilisateur.Dao;

import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoUtilisateur extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByUsernameAndStatusIsTrue(String username);
    List<Utilisateur> findAllByStatusIsTrue();

}
