package ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model;

public class Orangemoney {
    private String merchant_key="2358963";
    private String currency="XAF";
    private String order_id;
    private int amount;
    private String return_url="http://digniti/callback";
    private String cancel_url="http://digniti/callback";
    private String notif_url="http://digniti/callback";
    private String lang="fr";
    private String reference;

    public Orangemoney(String order_id, int amount, String reference) {
        this.order_id = order_id;
        this.amount = amount;
        this.reference = reference;
    }

    public Orangemoney() {
    }

    public String getMerchant_key() {
        return merchant_key;
    }

    public void setMerchant_key(String merchant_key) {
        this.merchant_key = merchant_key;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getCancel_url() {
        return cancel_url;
    }

    public void setCancel_url(String cancel_url) {
        this.cancel_url = cancel_url;
    }

    public String getNotif_url() {
        return notif_url;
    }

    public void setNotif_url(String notif_url) {
        this.notif_url = notif_url;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
