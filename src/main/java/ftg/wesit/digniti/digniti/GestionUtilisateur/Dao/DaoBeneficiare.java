package ftg.wesit.digniti.digniti.GestionUtilisateur.Dao;

import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Beneficiare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoBeneficiare extends JpaRepository<Beneficiare,String> {


    List<Beneficiare> findBeneficiareByEtatpaymentIsTrue();
    List<Beneficiare> findBeneficiareByEtatpaymentIsFalse();
    Beneficiare findBeneficiareByPhoneAndEtatpaymentIsFalse(String phone);
}
