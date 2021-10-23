package ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model;

import javax.persistence.*;

@Entity
public class TransactionDisbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int montant;
    private String phone;
    private String status;
    private String reference;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
