package ftg.wesit.digniti.digniti.GestionPayement.Stripe.RestController;

import com.stripe.exception.StripeException;
import ftg.wesit.digniti.digniti.GestionPayement.Stripe.Metier.MetierStripe;
import ftg.wesit.digniti.digniti.GestionPayement.Stripe.Model.Paymentstripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("stripepayment")
public class Stripepayment {

    @Autowired
    MetierStripe metierStripe;

    @GetMapping("payement/{montant}")
    Paymentstripe do_payment(@PathVariable int montant) throws StripeException {
        return metierStripe.do_payment(montant);
    }
    @PostMapping("payement/")
    String payment(@RequestBody Paymentstripe paymentstripe) throws StripeException {
        return metierStripe.paymentWithCheckoutPage(paymentstripe);
    }
}
