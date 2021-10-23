package ftg.wesit.digniti.digniti.GestionPayement.Transaction.Service;


import ftg.wesit.digniti.digniti.Configuration.GestionErreurs.ErrorMessages;
import ftg.wesit.digniti.digniti.GestionPayement.CONSTANTE;
import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Metier.MetierMOMO;
import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model.ResponseToPay;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Metier.MetierOrangeMoney;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.Orangemoney;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.ResultatOrange;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Dao.DaoTransDisbursement;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Dao.DaotransCollection;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Metier.MetierTransaction;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionCollection;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.Responsetransaction;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionDisbursement;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Metier.MetierBeneficiare;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Beneficiare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class ServiceTransaction implements MetierTransaction {
    @Autowired
    DaotransCollection daotransaction;
    @Autowired
    MetierOrangeMoney metierOrangeMoney;
    @Qualifier("collectionservice")
    @Autowired
    MetierMOMO metiercollection;
    @Qualifier("disbursementService")
    @Autowired
    MetierMOMO metierdibursement;
    @Autowired
    DaoTransDisbursement daoTransDisbursement;
    @Autowired
    MetierBeneficiare metierBeneficiare;

    static SecureRandom rnd = new SecureRandom();


    @Override
    public Responsetransaction do_collection(TransactionCollection model) {
        Responsetransaction responsetransaction = new Responsetransaction();
        if (model.getModepayment().equals(CONSTANTE.MOMO)) {
            model.setPhone(give_forma_contact(model.getPhone()));
            model.setReference(UUID.randomUUID().toString());
            if (metiercollection.requesttopay(model.getReference(), model.getPhone(), String.valueOf(model.getMontant()))) {
                ResponseToPay responseToPay = metiercollection.getstatus(model.getReference());
                responsetransaction.setStatus(responseToPay.getStatus());
                responsetransaction.setReference(responseToPay.getExternalId());
                model.setReference(responseToPay.getExternalId());
                model.setStatus(responseToPay.getStatus());
            }

        }else  if (model.getModepayment().equals(CONSTANTE.OM)){
            model.setReference(UUID.randomUUID().toString());
            ResultatOrange resultatOrange=  metierOrangeMoney.do_payment(new Orangemoney(model.getReference(), model.getMontant(),randomString(10)));
            model.setStatus("PENDING");
            model.setUrl_direction(resultatOrange.getPayment_url());
            responsetransaction.setUrl_direction(resultatOrange.getPayment_url());
        }
      responsetransaction.setModepayment(model.getModepayment());
        daotransaction.save(model);
        return responsetransaction;
    }

    @Override
    public Responsetransaction get_status_collection(String reference) {
        TransactionCollection transactionCollection=daotransaction.findByReference(reference);
        if (transactionCollection==null)throw new ErrorMessages("Transaction non trouvé",HttpStatus.NOT_FOUND);
        ResponseToPay responseToPay=metiercollection.getstatus(transactionCollection.getReference());
        Responsetransaction responsetransaction=new Responsetransaction();
        responsetransaction.setStatus(responseToPay.getStatus());
        responsetransaction.setReference(responseToPay.getExternalId());
        responsetransaction.setMontant(responseToPay.getAmount());
        responsetransaction.setModepayment("MOMO");
        transactionCollection.setStatus(responseToPay.getStatus());
        return responsetransaction;
    }

    @Override
    public String randomString(int taille) {
        String AB = "0123456789-AZERTYUIOPQSDFGJKLMWXCVBN";

        StringBuilder sb = new StringBuilder(taille);
        for (int i = 0; i < taille; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        System.out.println("*************************" + sb.toString());
        return sb.toString();
    }

    @Override
    public Responsetransaction do_payement(TransactionDisbursement disbursement) {
        Beneficiare beneficiare=metierBeneficiare.findBeneficiareByPhoneAndEtatpaymentIsFalse(disbursement.getPhone());
        if (beneficiare==null) throw new ErrorMessages("Le Beneficiare donct le contact est "+disbursement.getPhone()+" n'est pas dans le system",HttpStatus.CONFLICT);
        Responsetransaction responsetransaction=new Responsetransaction();
        disbursement.setReference(UUID.randomUUID().toString());
        if (metierdibursement.requesttopay(disbursement.getReference(), disbursement.getPhone(), String.valueOf(disbursement.getMontant()))){
            ResponseToPay responseToPay=metierdibursement.getstatus(disbursement.getReference());
            disbursement.setStatus(responseToPay.getStatus());
            responsetransaction.setStatus(responseToPay.getStatus());
            responsetransaction.setReference(responseToPay.getExternalId());
            responsetransaction.setPhone(responseToPay.getPayee().getPartyId());
            responsetransaction.setMontant(responseToPay.getAmount());
            beneficiare.setEtatpayment(true);
            beneficiare.setMontantpayer(Integer.parseInt(responseToPay.getAmount()));
            beneficiare.setStatus(responseToPay.getStatus());

        }
        daoTransDisbursement.save(disbursement);
        return responsetransaction;
    }

    @Override
    public List<Responsetransaction> do_AlltransDisbursement(List<TransactionDisbursement> transactionDisbursements) {
        List<Responsetransaction> responsetransactions=new ArrayList<>();
        transactionDisbursements.forEach(p->{
            responsetransactions.add(do_payement(p));
        });
        return responsetransactions;
    }

    @Override
    public List<TransactionDisbursement> findAlltransDisbursement() {
        return daoTransDisbursement.findAll();
    }

    @Override
    public List<TransactionCollection> findAlltransCollection() {
        return daotransaction.findAll();
    }


    String give_forma_contact(String contact) {
        int nbrdebit = contact.length();
        if (nbrdebit == 9) {
            contact = "237" + contact;
        } else if (nbrdebit != 12) {
            throw new ErrorMessages("Numero client incorrect ", HttpStatus.NOT_FOUND);
        }
        return contact;
    }

    @Bean
    void addbeneficiare(){
        metierBeneficiare.saveBeneficiare(new Beneficiare(5000,"237676012940","Fokou tamafo",CONSTANTE.IMPAYER));
        metierBeneficiare.saveBeneficiare(new Beneficiare(5000,"237676012941","Talom",CONSTANTE.IMPAYER));
        metierBeneficiare.saveBeneficiare(new Beneficiare(5000,"237676012942","Tatchum",CONSTANTE.IMPAYER));
        metierBeneficiare.saveBeneficiare(new Beneficiare(5000,"237677483076","Fomekong thérese",CONSTANTE.IMPAYER));
        metierBeneficiare.saveBeneficiare(new Beneficiare(5000,"237680421043","Kengne viviane",CONSTANTE.IMPAYER));

    }
}
