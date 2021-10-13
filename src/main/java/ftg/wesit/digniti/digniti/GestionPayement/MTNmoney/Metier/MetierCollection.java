package ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Metier;


import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model.Balance;
import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model.Basicuserinfo;
import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model.ResponseToPay;

public interface MetierCollection {
    Basicuserinfo basicuserinfo(String telephone);
    void getInfoHolder(String numero);
    boolean check_count(String numero);
    Balance getBalance();
    void deliverynotification(String reference, String notification);
    ResponseToPay getstatus(String reference);
    boolean requesttopay(String reference, String telephone, String montant);
}
