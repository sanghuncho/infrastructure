package auctionApi;

import java.io.FileInputStream;
import java.io.IOException;
import com.ebay.api.client.auth.oauth2.CredentialUtil;
import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.Environment;

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

    
    public static void main( String[] args ) throws IOException {
        OAuth2Api oauth2Api = new OAuth2Api();
        CredentialUtil.load(new FileInputStream("/infrastructure/src/java/resources/ebay-config.yaml"));
        //OAuth2Api.getApplicationToken();
    }
    
}
