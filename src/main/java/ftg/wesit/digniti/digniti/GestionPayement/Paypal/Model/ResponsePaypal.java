package ftg.wesit.digniti.digniti.GestionPayement.Paypal.Model;

public class ResponsePaypal {
    private String id;
    private String url_return;
    private String amount;
    private String current;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl_return() {
        return url_return;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public void setUrl_return(String url_return) {
        this.url_return = url_return;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
