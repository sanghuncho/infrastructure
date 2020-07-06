package auctionApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

/**
* 이베이 옥션 API
* 
* @author sanghuncho
*
* @since  27.06.2020
*
*/
public class ApiApp {
    //NOTE: Change this env to Environment.PRODUCTION to run this test in PRODUCTION
    private static final Environment EXECUTION_ENV = Environment.PRODUCTION;

    //private static final Environment EXECUTION_ENV = Environment.SANDBOX;
//    private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope", 
//            "https://api.ebay.com/oauth/api_scope/sell.marketing.readonly");
    
    static final String BROWSE_API = "https://api.ebay.com/buy/browse/v1/item/get_item_by_legacy_id?legacy_item_id=";
    private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope");
    
    public static void main( String[] args ) throws IOException {
        OAuth2Api oauth2Api = new OAuth2Api();
        CredentialUtil.load(new FileInputStream("C://Users/sanghuncho/git/infrastructure/src/java/resources/ebay-config.yaml"));
        OAuthResponse rep = oauth2Api.getApplicationToken(EXECUTION_ENV, authorizationScopesList);
        System.out.println(rep.getAccessToken().get().getToken());
        
        String getItemId = BROWSE_API + "224059691005";
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.set("Authorization", "Bearer " + rep.getAccessToken().get().getToken() );
        headers.set("X-EBAY-C-ENDUSERCTX", "contextualLocation=country=<2_character_country_code>,zip=<zip_code>" );
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
 
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        RestTemplate restTemplate = new RestTemplate();
 
        ResponseEntity<String> response = restTemplate.exchange(getItemId, //
                HttpMethod.GET, entity, String.class);
 
        String result = response.getBody();
 
        System.out.println(result);
    }
    
}
