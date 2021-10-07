package ftg.wesit.digniti.digniti.Configuration.Securite;


import ftg.wesit.digniti.digniti.Configuration.GestionErreurs.ErrorMessages;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Metier.MetierUtilisateur;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("serviceuserdetails")
public class ServiceUserDetails implements UserDetailsService {
    @Autowired
    MetierUtilisateur metierUtilisateur;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Utilisateur utilisateur = metierUtilisateur.findByUsernameAndStatusIsTrue(login);

        if (utilisateur==null)throw new ErrorMessages("Le partenaire n'a pas été identifié", HttpStatus.NOT_FOUND);
        Collection<GrantedAuthority> authorities=new ArrayList<>();//on a une collection de role
        utilisateur.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRolename()));
        });
        return new User(utilisateur.getUsername(),utilisateur.getPassword(),authorities);
    }
}
