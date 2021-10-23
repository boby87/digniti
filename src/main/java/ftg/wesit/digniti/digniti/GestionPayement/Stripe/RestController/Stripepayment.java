package ftg.wesit.digniti.digniti.GestionPayement.Stripe.RestController;

import com.stripe.exception.StripeException;
import ftg.wesit.digniti.digniti.GestionPayement.Stripe.Metier.PaymentMetier;
import ftg.wesit.digniti.digniti.GestionPayement.Stripe.Model.Paymentstripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("stripepayment")
public class Stripepayment {

    @Autowired
    PaymentMetier paymentMetier;

    @GetMapping("payement/{montant}")
    Paymentstripe do_payment(@PathVariable int montant) throws StripeException {
        return paymentMetier.do_payment(montant);
    }
    @PostMapping("payement/")
    String payment(@RequestBody Paymentstripe paymentstripe) throws StripeException {
        return paymentMetier.paymentWithCheckoutPage(paymentstripe);
    }
}
