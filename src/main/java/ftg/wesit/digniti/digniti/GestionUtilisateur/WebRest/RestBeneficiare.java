package ftg.wesit.digniti.digniti.GestionUtilisateur.WebRest;

import ftg.wesit.digniti.digniti.GestionUtilisateur.Metier.MetierBeneficiare;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Beneficiare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("beneficiare")
public class RestBeneficiare {
    @Autowired
    MetierBeneficiare metierBeneficiare;


    @GetMapping("all")
    List<Beneficiare> findAllBeneficaire(){
        return metierBeneficiare.findAllBeneficaire();
    }

    @GetMapping("all/success")
    List<Beneficiare> findAllBeneficairesuccess(){
        return metierBeneficiare.findBeneficiareByEtatpaymentIsTrue();
    }
}
