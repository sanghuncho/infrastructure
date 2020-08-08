package auctionApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.ebay.api.client.auth.oauth2.CredentialUtil;
import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.Environment;
import com.ebay.api.client.auth.oauth2.model.OAuthResponse;

public class GetSchippingCost {
    //NOTE: Change this env to Environment.PRODUCTION to run this test in PRODUCTION
    private static final Environment EXECUTION_ENV = Environment.PRODUCTION;
    
    static final String POST_API = "https://open.api.ebay.com/shopping";
    private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope");
    
    private static final int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static void main( String[] args ) throws IOException {
        
        List<String> itemNumberEbayList = Arrays.asList("362711397474");
        
        for (int i=0; i< itemNumberEbayList.size(); i++) {
            retrieveShippingCostData(itemNumberEbayList.get(i));
        }
    }
    
    private static void retrieveShippingCostData(String itemNumberEbay) throws IOException {
        OAuth2Api oauth2Api = new OAuth2Api();
        CredentialUtil.load(new FileInputStream("C://Users/sanghuncho/git/infrastructure/src/java/resources/ebay-config.yaml"));
        OAuthResponse rep = oauth2Api.getApplicationToken(EXECUTION_ENV, authorizationScopesList);

        String shopping_url="https://open.api.ebay.com/shopping?callname=GetShippingCosts&responseencoding=XML&appid=SanghunC-gkoo-PRD-1c8e8a00c-ff329d94&siteid=77&version=863&ItemID=" + itemNumberEbay +
                "&DestinationCountryCode=DE&IncludeDetails=true&QuantitySold=1";
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }));
        headers.setContentType(MediaType.APPLICATION_JSON);
   
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        RestTemplate restTemplate = new RestTemplate();
 
        ResponseEntity<String> response = restTemplate.exchange(shopping_url, 
                HttpMethod.GET, entity, String.class);
 
        String result = response.getBody();
        JSONObject soapDatainJsonObject = XML.toJSONObject(result);
        System.out.println(result);
        System.out.println("");
        JSONObject itemObjectJson = soapDatainJsonObject.getJSONObject("GetShippingCostsResponse");
        JSONObject shippingDetails = itemObjectJson.getJSONObject("ShippingDetails");
        System.out.println(shippingDetails);

        JSONObject shippingServiceOption = shippingDetails.getJSONObject("ShippingServiceOption");
        System.out.println(shippingServiceOption);
        JSONObject shippingServiceCost = shippingServiceOption.getJSONObject("ShippingServiceCost");
        System.out.println(shippingServiceCost.get("content"));
    }
    
    //json array
    private static void retrieveShippingCostOtherCase(String itemNumberEbay) throws IOException {
        OAuth2Api oauth2Api = new OAuth2Api();
        CredentialUtil.load(new FileInputStream("C://Users/sanghuncho/git/infrastructure/src/java/resources/ebay-config.yaml"));
        OAuthResponse rep = oauth2Api.getApplicationToken(EXECUTION_ENV, authorizationScopesList);

        String shopping_url="https://open.api.ebay.com/shopping?callname=GetShippingCosts&responseencoding=XML&appid=SanghunC-gkoo-PRD-1c8e8a00c-ff329d94&siteid=77&version=863&ItemID=" + itemNumberEbay +
                "&DestinationCountryCode=DE&IncludeDetails=true&QuantitySold=1";
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }));
        headers.setContentType(MediaType.APPLICATION_JSON);
   
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        RestTemplate restTemplate = new RestTemplate();
 
        ResponseEntity<String> response = restTemplate.exchange(shopping_url, 
                HttpMethod.GET, entity, String.class);
 
        String result = response.getBody();
        JSONObject soapDatainJsonObject = XML.toJSONObject(result);
        System.out.println(result);
        System.out.println("");
        //System.out.println(soapDatainJsonObject.toString());
        //System.out.println(soapDatainJsonObject.getJSONObject("GetShippingCostsResponse"));
        JSONObject itemObjectJson = soapDatainJsonObject.getJSONObject("GetShippingCostsResponse");
        //System.out.println(itemObjectJson.getJSONObject("ShippingDetails"));
        JSONObject shippingDetails = itemObjectJson.getJSONObject("ShippingDetails");
        System.out.println(shippingDetails);

//        System.out.println(shippingDetails.getJSONArray("ShippingServiceOption"));
//        JSONArray array = shippingDetails.getJSONArray("ShippingServiceOption");
//        
//        for (int i = 0; i < array.length(); ++i) {
//
//            JSONObject jsn = array.getJSONObject(i);
//            System.out.println(jsn.toString());
//        }
        
        JSONObject shippingServiceOption = shippingDetails.getJSONObject("ShippingServiceOption");
        System.out.println(shippingServiceOption);
        JSONObject shippingServiceCost = shippingServiceOption.getJSONObject("ShippingServiceCost");
        System.out.println(shippingServiceCost.get("content"));
    }
}
