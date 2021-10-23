package ftg.wesit.digniti.digniti.GestionPayement.Transaction.Dao;

import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionCollection;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionDisbursement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoTransDisbursement extends JpaRepository<TransactionDisbursement,String> {
}
