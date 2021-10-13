package ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model;

public class Requesttopay {
    private String amount;
    private String currency="XAF";
    private String externalId;
    private PayeurInfos payee;
    private String payerMessage;
    private String payeeNote;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public PayeurInfos getPayee() {
        return payee;
    }

    public void setPayee(PayeurInfos payee) {
        this.payee = payee;
    }

    public String getPayerMessage() {
        return payerMessage;
    }

    public void setPayerMessage(String payerMessage) {
        this.payerMessage = payerMessage;
    }

    public String getPayeeNote() {
        return payeeNote;
    }

    public void setPayeeNote(String payeeNote) {
        this.payeeNote = payeeNote;
    }
}
