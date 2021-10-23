package ftg.wesit.digniti.digniti.GestionUtilisateur.Service;

import ftg.wesit.digniti.digniti.GestionUtilisateur.Dao.DaoBeneficiare;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Metier.MetierBeneficiare;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Beneficiare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceBeneficiare implements MetierBeneficiare {
    @Autowired
    DaoBeneficiare daoBeneficiare;
    @Override
    public Beneficiare saveBeneficiare(Beneficiare beneficiare) {
        beneficiare.setEtatpayment(false);
        return daoBeneficiare.save(beneficiare);
    }

    @Override
    public List<Beneficiare> findAllBeneficaire() {
        return daoBeneficiare.findBeneficiareByEtatpaymentIsFalse();
    }

    @Override
    public Beneficiare findBeneficiareByPhoneAndEtatpaymentIsFalse(String phone) {
        return daoBeneficiare.findBeneficiareByPhoneAndEtatpaymentIsFalse(phone);
    }

    @Override
    public List<Beneficiare> findBeneficiareByEtatpaymentIsTrue() {
        return daoBeneficiare.findBeneficiareByEtatpaymentIsTrue();
    }
}
