package ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Collection.ImplMetier;

import com.fasterxml.jackson.databind.ObjectMapper;

import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Metier.MetierCollection;
import ftg.wesit.digniti.digniti.GestionPayement.MTNmoney.Model.*;
import net.minidev.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Base64;
import java.util.UUID;

@Service
@Transactional
public class ServiceColleSand implements MetierCollection {
    HttpClient httpclient = HttpClients.createDefault();
    String originalInput;//apiuse:apikey
    String Ocp_Apim_Subscription = "7020f827a2d948659aa151e3982b4e4e";
    String uuid;
    Apikey apikey;
    Token token;


    void apiuser() {
        try {
            URIBuilder builder = new URIBuilder("https://sandbox.momodeveloper.mtn.com/v1_0/apiuser");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            uuid = UUID.randomUUID().toString();

            request.setHeader("X-Reference-Id", uuid);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", Ocp_Apim_Subscription);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("providerCallbackHost", "digipay.cm");
            // Request body
            StringEntity reqEntity = new StringEntity(jsonObject.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                System.out.println(uuid);
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    void testuuid() {
        apiuser();
        try {
            URIBuilder builder = new URIBuilder("https://sandbox.momodeveloper.mtn.com/v1_0/apiuser/" + uuid);


            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", Ocp_Apim_Subscription);


            // Request body

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void getApi() {
        testuuid();
        try {
            URIBuilder builder = new URIBuilder("https://sandbox.momodeveloper.mtn.com/v1_0/apiuser/" + uuid + "/apikey");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", Ocp_Apim_Subscription);


            // Request body
            StringEntity reqEntity = new StringEntity("{body}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                apikey = new ObjectMapper().readValue(EntityUtils.toString(entity), Apikey.class);
                System.out.println("api key " + apikey.getApiKey());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    void getToken() {
        getApi();
        try {
            URIBuilder builder = new URIBuilder("https://sandbox.momodeveloper.mtn.com/collection/token/");
            originalInput = uuid + ":" + apikey.getApiKey();
            String base64 = Base64.getEncoder().encodeToString(originalInput.getBytes());
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Authorization", "Basic " + base64);
            request.setHeader("Ocp-Apim-Subscription-Key", Ocp_Apim_Subscription);


            // Request body
            StringEntity reqEntity = new StringEntity("{body}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                token = new ObjectMapper().readValue(EntityUtils.toString(entity), Token.class);
                System.out.println(token.getAccess_token());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void requesttopay() {
        getToken();
        try {
            URIBuilder builder = new URIBuilder("https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Authorization", "Bearer " + token.getAccess_token());
            // request.setHeader("X-Callback-Url", "");
            request.setHeader("X-Reference-Id", uuid);
            request.setHeader("X-Target-Environment", "sandbox");
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", Ocp_Apim_Subscription);
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonpayeur = new JSONObject();
            jsonpayeur.put("partyIdType", "MSISDN");
            jsonpayeur.put("partyId", "676012940");
            jsonObject.put("amount", "1000");
            jsonObject.put("currency", "EUR");
            jsonObject.put("externalId", "001");
            jsonObject.put("payer", jsonpayeur);
            jsonObject.put("payerMessage", "Fokou");
            jsonObject.put("payeeNote", "Test");
            // Request body

            StringEntity reqEntity = new StringEntity(jsonObject.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                System.out.println(jsonObject);
                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void requesttopayByRef() {
        requesttopay();
        try {
            URIBuilder builder = new URIBuilder("https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay/"+uuid);


            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Authorization", "Bearer " + token.getAccess_token());
            request.setHeader("X-Target-Environment", "sandbox");
            request.setHeader("Ocp-Apim-Subscription-Key", Ocp_Apim_Subscription);


            // Request body

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                ResponseToPay responseToPay = new ObjectMapper().readValue(EntityUtils.toString(entity), ResponseToPay.class);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    void deliverynotification(){
        try
        {
            URIBuilder builder = new URIBuilder("https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay/{referenceId}/deliverynotification");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("notificationMessage", "");
            request.setHeader("Language", "");
            request.setHeader("Authorization", "");
            request.setHeader("X-Target-Environment", "");
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "{subscription key}");


            // Request body
            StringEntity reqEntity = new StringEntity("{body}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Basicuserinfo basicuserinfo(String telephone) {
        return null;
    }

    @Override
    public void getInfoHolder(String numero) {

    }

    @Override
    public boolean check_count(String numero) {
        return false;
    }

    @Override
    public Balance getBalance() {
        return null;
    }

    @Override
    public void deliverynotification(String reference, String notification) {

    }

    @Override
    public ResponseToPay getstatus(String reference) {
        return null;
    }

    @Override
    public boolean requesttopay(String reference, String telephone, String montant) {
        return false;
    }
}