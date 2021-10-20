package ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Metier;


import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.Orangemoney;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.ResultatOrange;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.TokenOrange;

public interface MetierOrangeMoney {
    String url_prod="https://api.orange.com/orange-money-webpay/cm/v1/webpayment";
    String url_dev="https://api.orange.com/orange-money-webpay/dev/v1/webpayment";

    ResultatOrange do_payment(Orangemoney orangemoney);
    TokenOrange getTokenOrange();

}
