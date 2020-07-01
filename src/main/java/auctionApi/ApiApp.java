package auctionApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private static final Environment EXECUTION_ENV = Environment.SANDBOX;
//    private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope", 
//            "https://api.ebay.com/oauth/api_scope/sell.marketing.readonly");
//    
    private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope");
    
    public static void main( String[] args ) throws IOException {
        OAuth2Api oauth2Api = new OAuth2Api();
        CredentialUtil.load(new FileInputStream("C://Users/sanghuncho/git/infrastructure/src/java/resources/ebay-config.yaml"));
//        List<String> scopes = new ArrayList<>();
//        scopes.add("https://api.ebay.com/oauth/api_scope");
//        scopes.add("https://api.ebay.com/oauth/api_scope/buy.offer.auction");
        OAuthResponse rep = oauth2Api.getApplicationToken(EXECUTION_ENV, authorizationScopesList);
        System.out.println(rep.toString());
    }
    
}
