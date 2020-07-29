package auctionApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
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

public class TrackingApi {
	 //NOTE: Change this env to Environment.PRODUCTION to run this test in PRODUCTION
    private static final Environment EXECUTION_ENV = Environment.PRODUCTION;
    private static final String IMAGE_DIR_HOME = "C:/Users/sanghuncho/Pictures/ebay/";
    private static final String IMAGE_DIR_OFFICE = "C:/Users/sanghuncho/Documents/Gkoo/ebay/";
    private static final String IMAGE_DIR = IMAGE_DIR_OFFICE;
    
    static final String BROWSE_API = "https://api.ebay.com/post-order/v2/return/372950538367/tracking";
    private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope");
    
    public static void main( String[] args ) throws IOException {
        String itemNumberEbay = "333640534104";
        int itemNumberGkoo = 200;
        
        retrieveProductData(itemNumberEbay, itemNumberGkoo);
    }
    
    private static void retrieveProductData(String itemNumberEbay, int itemNumberGkoo) throws IOException {
        OAuth2Api oauth2Api = new OAuth2Api();
        CredentialUtil.load(new FileInputStream("C://Users/sanghuncho/git/infrastructure/src/java/resources/ebay-config.yaml"));
        OAuthResponse rep = oauth2Api.getApplicationToken(EXECUTION_ENV, authorizationScopesList);
        System.out.println(rep.getAccessToken().get().getToken());
        
//        String getItemId = BROWSE_API + itemNumberEbay;
//        int itemNr = itemNumberGkoo;
        String token = "AgAAAA**AQAAAA**aAAAAA**QMkWXw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AFlISgCpaCowudj6x9nY+seQ**nFsGAA**AAMAAA**F5ZSbkRKk9FjDgQeivEZhQ5svQnJPgKs3WuX/0JM7o6VxmJTn8uGUR0hja6rbFb99ySyGIA/4FGr/zlK3/qO/22yb7th6xNL8/7CDey78xFQG/QvKiKzQae3gHMoD5MjnaOJEOx92AcOUrpqY3d9oDnUaPQdu4GPsE89YawPZ9MlTBA1KSP0cN1oEIRTR61uFv0o9GHkw0FGOPLPwb2YwQdwXt5uwcxJSlzBCjis+Y7EvOjpR/lITiHKp4KelXRNFih4f9Qsf9zkf7GEL63bT451+G83+YTlNxwl4EmG6K/DwEl/FVoN1b/hcQndiFVDwvcZT6eQ+qKUeQWdMudDbpXAScbxT4FQMNTKApZTJZRMnAx482We23VR96wmGKtSlEoVTJ5fOjkmT5T02noWEAlOLYH66GK81/sbMx/Hu/yrxOHLRiJOg/zHQRa0xJ4xro9zSIhMOsr91ECIIYNQDanF3vxJ2L5x/p1ZKDN0HJ8tTNd75OLOgjhkHRzDw65FHwiri81cqeYUT+LWuuPscm9ChdMRGZ//FqmzrQjTY+nyOlIOrayHeTKJJk5qlQ+PIT8hkkP1KnFOSDvoQxJsglWUMlryMnaxM/ny5Vn0sqH1AgXPYHTwZ01iMAfuDK4RenN2+pIU4TF+ZZ7W/4Qsy0efvPs21rxmU+4Gv2J8s9wYB3lEr5ORIU+fwsC7Eu1707vL6WsyTTWDpoNY7UL2EIMknjX24Atd/2UOCLKhwvyT/cVhGxNbHScGy2zkN+wY";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token );
        headers.set("X-EBAY-C-ENDUSERCTX", "contextualLocation=country=<2_character_country_code>,zip=<zip_code>" );
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
 
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        RestTemplate restTemplate = new RestTemplate();
 
        ResponseEntity<String> response = restTemplate.exchange(BROWSE_API, 
                HttpMethod.GET, entity, String.class);
 
        String result = response.getBody();
        JSONObject myObject = new JSONObject(result);
    }
}
