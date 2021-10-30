package ftg.wesit.digniti.digniti.GestionPayement.Paypal.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.*;
import ftg.wesit.digniti.digniti.Configuration.GestionErreurs.ErrorMessages;
import ftg.wesit.digniti.digniti.GestionPayement.Paypal.Metier.MetierPaypal;
import ftg.wesit.digniti.digniti.GestionPayement.Paypal.Model.Order;
import ftg.wesit.digniti.digniti.GestionPayement.Paypal.Model.ResponsePaypal;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Dao.DaotransCollection;
import ftg.wesit.digniti.digniti.GestionPayement.Transaction.Model.TransactionCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalService implements MetierPaypal {

    @Autowired
    private APIContext apiContext;
    @Autowired
    DaotransCollection daotransaction;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @Override
    public ResponsePaypal createPayment(Order order) {
        ResponsePaypal responsePaypal = new ResponsePaypal();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        order.setPrice(new BigDecimal(order.getPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        amount.setTotal(String.format("%.3f", order.getPrice()));

        Transaction transaction = new Transaction();
        transaction.setDescription(order.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/" + CANCEL_URL);
        redirectUrls.setReturnUrl("http://localhost:8080/" + SUCCESS_URL);
        payment.setRedirectUrls(redirectUrls);
        try {
            payment = payment.create(apiContext);
            System.out.println(payment.toJSON());
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        responsePaypal.setId(payment.getId());
        responsePaypal.setAmount(payment.getTransactions().get(0).getAmount().getTotal());
        responsePaypal.setCurrent(payment.getTransactions().get(0).getAmount().getCurrency());
        responsePaypal.setStatus(payment.getState());
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                responsePaypal.setUrl_return(link.getHref());
            }
        }
        return responsePaypal;
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        payment=payment.execute(apiContext, paymentExecute);
        TransactionCollection transactionCollection=daotransaction.findByReference(payment.getId());
        if (transactionCollection==null)throw new ErrorMessages("Le payement a rancontré des soucis", HttpStatus.CONFLICT);
        transactionCollection.setStatus(payment.getState());
        System.out.println(payment.toJSON());
        return payment;
    }

}