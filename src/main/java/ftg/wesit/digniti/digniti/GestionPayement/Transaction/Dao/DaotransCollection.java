package ftg.wesit.digniti.digniti.GestionPayement.Transaction.Dao;

import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaotransCollection extends JpaRepository<TransactionCollection,String> {
    TransactionCollection findByReference(String reference);
}
