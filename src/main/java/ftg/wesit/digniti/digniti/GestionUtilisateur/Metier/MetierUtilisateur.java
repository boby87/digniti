package ftg.wesit.digniti.digniti.GestionUtilisateur.Metier;

import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Roles;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Utilisateur;

import java.util.List;

public interface MetierUtilisateur {

    Utilisateur save(Utilisateur utilisateur);
    Utilisateur findByUsernameAndStatusIsTrue(String username);
    List<Utilisateur> findAllByStatusIsTrue();
    List<Utilisateur> findAll();
    Roles findByRolename(String rolename);
    Roles save(Roles role);
    void addroleToUtilisateur(String rolename,String usename);
}
