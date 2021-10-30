package ftg.wesit.digniti.digniti.GestionPayement.Paypal.Metier;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import ftg.wesit.digniti.digniti.GestionPayement.Paypal.Model.Order;
import ftg.wesit.digniti.digniti.GestionPayement.Paypal.Model.ResponsePaypal;

public interface MetierPaypal {
    ResponsePaypal createPayment(Order order);
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
