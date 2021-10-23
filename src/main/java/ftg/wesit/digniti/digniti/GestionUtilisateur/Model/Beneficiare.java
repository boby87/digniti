package ftg.wesit.digniti.digniti.GestionUtilisateur.Model;

import javax.persistence.*;

@Entity
public class Beneficiare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int montant;
    private int montantpayer;//ceci represente le montant qu il a recu
    @Column(unique = true,nullable = false)
    private String phone;
    private String beneficiare;
    private String status;//cette variable donne l'etat je veux dire PENDING SUCCESSFULL
    private boolean etatpayment;

    public Beneficiare() {
    }

    public Beneficiare(int montant, String phone, String beneficiare,String status) {
        this.montant = montant;
        this.phone = phone;
        this.beneficiare = beneficiare;
        this.status=status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBeneficiare() {
        return beneficiare;
    }

    public void setBeneficiare(String beneficiare) {
        this.beneficiare = beneficiare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEtatpayment() {
        return etatpayment;
    }

    public void setEtatpayment(boolean etatpayment) {
        this.etatpayment = etatpayment;
    }

    public int getMontantpayer() {
        return montantpayer;
    }

    public void setMontantpayer(int montantpayer) {
        this.montantpayer = montantpayer;
    }
}
