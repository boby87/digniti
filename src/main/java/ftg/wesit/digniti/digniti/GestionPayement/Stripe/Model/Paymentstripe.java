package ftg.wesit.digniti.digniti.GestionPayement.Stripe.Model;

public class Paymentstripe {
    private String id;
    private String object;
    private int amount;
    private int amount_capturable;
    private int amount_received;
    private String capture_method="automatic";
    private String client_secret;
    private String confirmation_method;
    private String created;
    private String[] payment_method_types;
    private String currency;
    // our success and cancel url stripe will redirect to this links
    private String successUrl;
    private String cancelUrl;
    private long quantity;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount_capturable() {
        return amount_capturable;
    }

    public void setAmount_capturable(int amount_capturable) {
        this.amount_capturable = amount_capturable;
    }

    public int getAmount_received() {
        return amount_received;
    }

    public void setAmount_received(int amount_received) {
        this.amount_received = amount_received;
    }

    public String getCapture_method() {
        return capture_method;
    }

    public void setCapture_method(String capture_method) {
        this.capture_method = capture_method;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getConfirmation_method() {
        return confirmation_method;
    }

    public void setConfirmation_method(String confirmation_method) {
        this.confirmation_method = confirmation_method;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String[] getPayment_method_types() {
        return payment_method_types;
    }

    public void setPayment_method_types(String[] payment_method_types) {
        this.payment_method_types = payment_method_types;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
