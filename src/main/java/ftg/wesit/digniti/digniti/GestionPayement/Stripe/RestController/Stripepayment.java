package ftg.wesit.digniti.digniti.GestionPayement.Stripe.RestController;

import com.stripe.exception.StripeException;
import ftg.wesit.digniti.digniti.GestionPayement.Stripe.Metier.PaymentMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("stripepayment")
public class Stripepayment {

    @Autowired
    PaymentMetier paymentMetier;

    @GetMapping("payement/{montant}")
    String do_payment(@PathVariable int montant) throws StripeException {
        return paymentMetier.do_payment(montant);
    }
}
