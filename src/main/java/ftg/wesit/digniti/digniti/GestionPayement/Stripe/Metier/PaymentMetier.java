package ftg.wesit.digniti.digniti.GestionPayement.Stripe.Metier;

import com.stripe.exception.StripeException;

public interface PaymentMetier {
    String do_payment(int montant) throws StripeException;
}
