package ftg.wesit.digniti.digniti.GestionUtilisateur.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idrole;
    @Column(unique = true)
    private String rolename;

    public Roles(String rolename) {
        this.rolename = rolename;
    }

    public Roles() {
    }

    public Long getIdrole() {
        return idrole;
    }

    public void setIdrole(Long idrole) {
        this.idrole = idrole;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
