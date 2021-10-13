package ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model;

public class ResponseToPay {
    private String amount;
    private String currency;
    private String externalId;
    private PayeurInfos payer;
    private String payerMessage;
    private String payeeNote;
    private String status;
    private String financialTransactionId;
    private String reason;


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

    public PayeurInfos getPayer() {
        return payer;
    }

    public void setPayer(PayeurInfos payer) {
        this.payer = payer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFinancialTransactionId() {
        return financialTransactionId;
    }

    public void setFinancialTransactionId(String financialTransactionId) {
        this.financialTransactionId = financialTransactionId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
