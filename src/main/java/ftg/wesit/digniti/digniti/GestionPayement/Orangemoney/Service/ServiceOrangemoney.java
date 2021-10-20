package ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Metier.MetierOrangeMoney;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.Orangemoney;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.ResultatOrange;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.TokenOrange;
import net.minidev.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ServiceOrangemoney implements MetierOrangeMoney {
    private final RestTemplate restTemplate;
    HttpClient httpclient = HttpClients.createDefault();
    public HttpHeaders headers = new HttpHeaders();
    TokenOrange tokenOrange = new TokenOrange();
    String url_prod="https://api.orange.com/orange-money-webpay/cm/v1/webpayment";
    String url_dev="https://api.orange.com/orange-money-webpay/dev/v1/webpayment";

    public ServiceOrangemoney(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @Override
    public ResultatOrange do_payment(Orangemoney orangemoney) {
        getTokenOrange();
        ResultatOrange resultatOrange=new ResultatOrange();
        headers = new HttpHeaders();

       // headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer "+tokenOrange.getAccess_token());
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("merchant_key",orangemoney.getMerchant_key());
            jsonObject.put("currency",orangemoney.getCurrency());
            jsonObject.put("order_id", UUID.randomUUID().toString());
            jsonObject.put("amount",orangemoney.getAmount());
            jsonObject.put("return_url",orangemoney.getReturn_url());
            jsonObject.put("cancel_url",orangemoney.getCancel_url());
            jsonObject.put("notif_url",orangemoney.getNotif_url());
            jsonObject.put("lang",orangemoney.getLang());
            jsonObject.put("reference",orangemoney.getReference());
            // Request body
        System.out.println(jsonObject.toString());
        HttpEntity<String> entity = new HttpEntity(jsonObject.toString(), headers);

        String res = restTemplate.postForObject(url_dev, entity, String.class);
        try {
            resultatOrange = new ObjectMapper().readValue(res, ResultatOrange.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(resultatOrange.toString());

                System.out.println("/*--*//*--*//*--*//*--*//*---*///*---*///*---**///*---*-**////*---***-*/////");


        return resultatOrange;
    }

    @Override
    public TokenOrange getTokenOrange() {
    headers = new HttpHeaders();

    headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    headers.add("Authorization", "Basic xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx==");

    HttpEntity<String> entity = new HttpEntity("grant_type=client_credentials", headers);

    String res = restTemplate.postForObject("https://api.orange.com/oauth/v2/token", entity, String.class);
    System.out.println(res);

        try {
            tokenOrange = new ObjectMapper().readValue(res, TokenOrange.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return tokenOrange;
    }


}
