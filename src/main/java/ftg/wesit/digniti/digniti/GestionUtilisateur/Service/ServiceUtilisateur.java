package ftg.wesit.digniti.digniti.GestionUtilisateur.Service;

import ftg.wesit.digniti.digniti.CONSTANTROLE;
import ftg.wesit.digniti.digniti.Configuration.GestionErreurs.ErrorMessages;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Dao.DaoRoles;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Dao.DaoUtilisateur;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Metier.MetierUtilisateur;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Roles;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ServiceUtilisateur implements MetierUtilisateur {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    DaoUtilisateur daoUtilisateur;

    @Autowired
    DaoRoles daoRoles;


    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        if (findByUsernameAndStatusIsTrue(utilisateur.getUsername())!=null) throw new ErrorMessages("Ce numéro est deja utilisé par un autre utilisateur!!!", HttpStatus.CONFLICT);

        if (utilisateur.getPassword()!=null){
            String hashpw=bCryptPasswordEncoder.encode(utilisateur.getPassword());
            utilisateur.setPassword(hashpw);
        }else {
            throw new ErrorMessages("Bien vouloir entrer le mot de passe", HttpStatus.CONFLICT);
        }
        utilisateur.setStatus(true);
        return daoUtilisateur.save(utilisateur);
    }

    @Override
    public Utilisateur findByUsernameAndStatusIsTrue(String username) {
        return daoUtilisateur.findByUsernameAndStatusIsTrue(username);
    }

    @Override
    public List<Utilisateur> findAllByStatusIsTrue() {
        return daoUtilisateur.findAllByStatusIsTrue();
    }

    @Override
    public List<Utilisateur> findAll() {
        return daoUtilisateur.findAll();
    }

    @Override
    public Roles findByRolename(String rolename) {
        return daoRoles.findByRolename(rolename);
    }

    @Override
    public Roles save(Roles role) {
        return daoRoles.save(role);
    }

    @Override
    public void addroleToUtilisateur(String rolename, String usename) {
        Utilisateur utilisateur=findByUsernameAndStatusIsTrue(usename);
        if (utilisateur==null)throw new ErrorMessages("L'utilisateur n'existe pas ", HttpStatus.NOT_FOUND);
        Roles roles=findByRolename(rolename);
        if (roles==null)throw new ErrorMessages("Bien vouloir créer le role car elle n'existe pas ", HttpStatus.NOT_FOUND);
        utilisateur.getRoles().add(roles);
    }

    @Override
    public List<Utilisateur> findAllemail(String email) {
        return daoUtilisateur.findAllemail(email);
    }


    @Bean
    void addRole(){
        if (daoRoles.findAll()==null){
            save(new Roles(CONSTANTROLE.ADMIN));
            save(new Roles(CONSTANTROLE.VISTITEUR));
        }

    }

}
