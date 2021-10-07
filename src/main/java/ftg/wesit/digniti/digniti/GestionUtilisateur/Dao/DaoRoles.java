package ftg.wesit.digniti.digniti.GestionUtilisateur.Dao;

import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoRoles extends JpaRepository<Roles,Long> {
    Roles findByRolename(String rolename);
}
