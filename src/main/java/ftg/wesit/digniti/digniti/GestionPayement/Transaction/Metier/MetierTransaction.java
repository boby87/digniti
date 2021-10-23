package ftg.wesit.digniti.digniti.GestionPayement.Transaction.Metier;

import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionCollection;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.Responsetransaction;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionDisbursement;

import java.util.List;

public interface MetierTransaction {
    Responsetransaction do_collection(TransactionCollection collection);
    Responsetransaction get_status_collection(String reference);
    List<TransactionCollection> findAlltransCollection();
    String randomString(int lengt);
    Responsetransaction do_payement(TransactionDisbursement disbursement);
    List<Responsetransaction> do_AlltransDisbursement(List<TransactionDisbursement> transactionDisbursements);
    List<TransactionDisbursement> findAlltransDisbursement();
}
