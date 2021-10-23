package ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.RestController;

import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Metier.MetierMOMO;
import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model.Balance;
import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model.Basicuserinfo;
import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model.ResponseToPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("collection")
public class Collection {
    @Qualifier("collectionservice")
    @Autowired
    MetierMOMO metierCollection;

    @GetMapping("requesttopay/{reference}/{telephone}/{montant}")
    boolean requesttopay(@PathVariable String reference,@PathVariable String telephone,@PathVariable String montant){
        return metierCollection.requesttopay(reference,telephone,montant);
    }

    @GetMapping("requesttopay/{reference}")
    ResponseToPay getstatus(@PathVariable String reference){
        return metierCollection.getstatus(reference);
    }
    @GetMapping("deliverynotification/{reference}/{notification}")
    public void deliverynotification(@PathVariable String reference,@PathVariable String notification){

         metierCollection.deliverynotification(reference, notification);
    }

    @GetMapping("balance/")
    Balance getBalance(){
        return metierCollection.getBalance();
    }
    @GetMapping("info/{telephone}")
    Basicuserinfo basicuserinfo(@PathVariable String telephone){
        return metierCollection.basicuserinfo(telephone);
    }
    @GetMapping("info/active/{numero}")
    void getInfoHolder(@PathVariable String numero){
        metierCollection.getInfoHolder(numero);
    }
}
