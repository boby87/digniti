package ftg.wesit.digniti.digniti.GestionPayement.Stripe.Metier;

import com.stripe.exception.StripeException;
import ftg.wesit.digniti.digniti.GestionPayement.Stripe.Model.Paymentstripe;

public interface PaymentMetier {
    Paymentstripe do_payment(int montant) throws StripeException;

    String  paymentWithCheckoutPage(Paymentstripe payment) throws StripeException;
}
