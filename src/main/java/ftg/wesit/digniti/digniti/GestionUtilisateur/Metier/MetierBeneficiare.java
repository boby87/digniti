package ftg.wesit.digniti.digniti.GestionUtilisateur.Metier;

import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Beneficiare;

import java.util.List;

public interface MetierBeneficiare {

    Beneficiare saveBeneficiare(Beneficiare beneficiare);
    List<Beneficiare> findAllBeneficaire();
    Beneficiare findBeneficiareByPhoneAndEtatpaymentIsFalse(String phone);
    List<Beneficiare> findBeneficiareByEtatpaymentIsTrue();
}
