package ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model;

public class Responsetransaction {
    private String status;
    private String reference;
    private String url_direction;
    private String phone;
    private String montant;
    private String modepayment;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getModepayment() {
        return modepayment;
    }

    public void setModepayment(String modepayment) {
        this.modepayment = modepayment;
    }
}
