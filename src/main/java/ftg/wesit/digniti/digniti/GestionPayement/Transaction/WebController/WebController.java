package ftg.wesit.digniti.digniti.GestionPayement.Transaction.WebController;

import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.Callbackorange;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Metier.MetierTransaction;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.Responsetransaction;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionCollection;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionDisbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("api")
@RestController
public class WebController {

    @Autowired
    MetierTransaction metierTransaction;

    @PostMapping("collection")
    Responsetransaction do_collection(@RequestBody TransactionCollection collection){
        return metierTransaction.do_collection(collection);
    }

    @PostMapping("disbursement")
    Responsetransaction do_payement(@RequestBody TransactionDisbursement disbursement){
        return metierTransaction.do_payement(disbursement);
    }

    @PostMapping("disbursements")
    List<Responsetransaction> do_AlltransDisbursement(@RequestBody List<TransactionDisbursement> transactionDisbursements){
        return metierTransaction.do_AlltransDisbursement(transactionDisbursements);
    }


    @GetMapping("all/collections")
    List<TransactionCollection> findAlltransCollection(){
        return metierTransaction.findAlltransCollection();
    }

    @GetMapping("all/disbursements")
    List<TransactionDisbursement> findAlltransDisbursement(){
        return metierTransaction.findAlltransDisbursement();
    }

    @GetMapping("collection/status/{reference}")
    Responsetransaction get_status_collection(@PathVariable String reference){
        return metierTransaction.get_status_collection(reference);
    }

    @PutMapping("callback")
    void callback_orange(@RequestBody Callbackorange callbackorange){
       System.out.println(callbackorange.toString());
    }
}
