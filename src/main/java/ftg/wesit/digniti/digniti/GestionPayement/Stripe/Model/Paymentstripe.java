package ftg.wesit.digniti.digniti.GestionPayement.Stripe.Model;

public class Paymentstripe {
    private String id;
    private String object;
    private int amount;
    private int amount_capturable;
    private int amount_received;
    private String capture_method="automatic";
    private String client_secret;
    private String confirmation_method;
    private String created;
    private String[] payment_method_types;
}
