package ftg.wesit.digniti.digniti.GestionPayement.Paypal.Model;

public class Order {
    private double price=1000;
    private String currency="USD";
    private String method="paypal";
    private String intent="sale";
    private String description="fokou le test de pays pal";
    private String cancelUrl;
    private String successUr;

    public Order() {
    }

    public Order(double price, String description) {
        this.price = price;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getSuccessUr() {
        return successUr;
    }

    public void setSuccessUr(String successUr) {
        this.successUr = successUr;
    }
}
