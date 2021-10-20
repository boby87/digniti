package ftg.wesit.digniti.digniti.GestionPayement.Stripe.ImplMetier;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import ftg.wesit.digniti.digniti.GestionPayement.Stripe.Metier.PaymentMetier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImplementPayment implements PaymentMetier {
   // @Value("${Stripe.apiKey}")
    String apiKey="sk_test_51IpZV1LuSvUE1f51R1TUnigMEdhGGPA0xVTTevoGlGfUkA8VzrFXHM6WImSicm32917FUBxNydNngebtlXiOlAGg00ubfEUKjE";

    @Override
    public String do_payment(int montant) throws StripeException {
        Stripe.apiKey = apiKey;
        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("bancontact");
        paymentMethodTypes.add("card");
        paymentMethodTypes.add("eps");
        paymentMethodTypes.add("giropay");
        paymentMethodTypes.add("ideal");
        paymentMethodTypes.add("p24");
        paymentMethodTypes.add("sepa_debit");
        paymentMethodTypes.add("sofort");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", montant);
        params.put("currency", "eur");
        params.put("payment_method_types", paymentMethodTypes);

        String paymentIntent = PaymentIntent.create(params).toJson();

        return paymentIntent;
    }

}
