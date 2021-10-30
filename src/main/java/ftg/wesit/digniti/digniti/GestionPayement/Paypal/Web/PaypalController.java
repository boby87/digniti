package ftg.wesit.digniti.digniti.GestionPayement.Paypal.Web;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import ftg.wesit.digniti.digniti.GestionPayement.Paypal.Metier.MetierPaypal;
import ftg.wesit.digniti.digniti.GestionPayement.Paypal.Model.Order;
import ftg.wesit.digniti.digniti.GestionPayement.Paypal.Model.ResponsePaypal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaypalController {

        @Autowired
        MetierPaypal metierPaypal;

        public static final String SUCCESS_URL = "pay/success";
        public static final String CANCEL_URL = "pay/cancel";


        @PostMapping("/pay")
        public ResponsePaypal payment(@RequestBody Order order) {
            return metierPaypal.createPayment(order);
        }

        @GetMapping(value = CANCEL_URL)
        public String cancelPay() {
            return "cancel";
        }

        @GetMapping(value = SUCCESS_URL)
        public Payment successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {

            Payment payment = null;
            try {
                payment = metierPaypal.executePayment(paymentId, payerId);
            } catch (PayPalRESTException e) {
                e.printStackTrace();
            }

            return payment;
        }

    }

