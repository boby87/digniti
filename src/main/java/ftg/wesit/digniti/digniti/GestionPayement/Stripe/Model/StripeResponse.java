package ftg.wesit.digniti.digniti.GestionPayement.Stripe.Model;

public class StripeResponse {
    private String id;
    private String object;
    private String amount;
    private String amount_refunded;
    private String application;
    private String application_fee;
    private String application_fee_amount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount_refunded() {
        return amount_refunded;
    }

    public void setAmount_refunded(String amount_refunded) {
        this.amount_refunded = amount_refunded;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getApplication_fee() {
        return application_fee;
    }

    public void setApplication_fee(String application_fee) {
        this.application_fee = application_fee;
    }

    public String getApplication_fee_amount() {
        return application_fee_amount;
    }

    public void setApplication_fee_amount(String application_fee_amount) {
        this.application_fee_amount = application_fee_amount;
    }
}
