package ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model;

public class PayeurInfos {
    private String partyIdType="MSISDN";
    private String partyId;

    public String getPartyIdType() {
        return partyIdType;
    }

    public void setPartyIdType(String partyIdType) {
        this.partyIdType = partyIdType;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
}
