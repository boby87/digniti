package ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model;

import javax.persistence.*;

@Entity
public class TransactionCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String modepayment;
    private int montant;
    private String phone;
    private String status;
    @Column(unique = true)
    private String reference;
    private String url_direction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModepayment() {
        return modepayment;
    }

    public void setModepayment(String modepayment) {
        this.modepayment = modepayment;
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

    public String getUrl_direction() {
        return url_direction;
    }

    public void setUrl_direction(String url_direction) {
        this.url_direction = url_direction;
    }
}
