package ftg.wesit.digniti.digniti.GestionUtilisateur.WebRest;

import ftg.wesit.digniti.digniti.GestionUtilisateur.Metier.MetierUtilisateur;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("utilisateur")
public class RestUtilisateur {
    @Autowired
    MetierUtilisateur metierUtilisateur;

    @GetMapping("getAll")
    List<Utilisateur> getAllUtilisateur(){
        return metierUtilisateur.findAll();
    }

    @GetMapping("getAll/active")
    List<Utilisateur> getAllUtilisateurActive(){
        return metierUtilisateur.findAllByStatusIsTrue();
    }
    @GetMapping("getBy/usename/{username}")
    Utilisateur getByUsername(@PathVariable String username){
        return metierUtilisateur.findByUsernameAndStatusIsTrue(username);
    }
    @GetMapping("getBy/email/{email}")
    List<Utilisateur> getByEmail(@PathVariable String email){
        return metierUtilisateur.findAllemail(email);
    }
    @PostMapping("register")
    Utilisateur registerUtilisateur(Utilisateur utilisateur){
        return metierUtilisateur.save(utilisateur);
    }
}
