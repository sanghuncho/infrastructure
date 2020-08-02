package auctionApi;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.http.entity.StringEntity;
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

public class EbayPostApi {
    //NOTE: Change this env to Environment.PRODUCTION to run this test in PRODUCTION
    private static final Environment EXECUTION_ENV = Environment.PRODUCTION;
    
    static final String POST_API = "https://open.api.ebay.com/shopping";
    private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope");
    
    private static final int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static void main( String[] args ) throws IOException {
        
        List<String> itemNumberEbayList = Arrays.asList("313160996654");
        
        for (int i=0; i< itemNumberEbayList.size(); i++) {
            retrieveProductData(itemNumberEbayList.get(i));
        }
    }
    
    private static void retrieveProductData(String itemNumberEbay) throws IOException {
        OAuth2Api oauth2Api = new OAuth2Api();
        CredentialUtil.load(new FileInputStream("C://Users/sanghuncho/git/infrastructure/src/java/resources/ebay-config.yaml"));
        OAuthResponse rep = oauth2Api.getApplicationToken(EXECUTION_ENV, authorizationScopesList);
        
        //high bidder, bidcount
        //String shopping_url="https://open.api.ebay.com/shopping?callname=GetItemStatus&responseencoding=XML&appid=SanghunC-gkoo-PRD-1c8e8a00c-ff329d94&siteid=77&version=863&ItemID=313160996654";

        String shopping_url="https://open.api.ebay.com/shopping?callname=GetSingleItem&responseencoding=XML&appid=SanghunC-gkoo-PRD-1c8e8a00c-ff329d94&siteid=77&version=863&ItemID=313160996654";
        //browser api - minimumPriceToBid
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", "Bearer " + rep.getAccessToken().get().getToken() );
//        headers.set("X-EBAY-API-APP-ID", "SanghunC-gkoo-PRD-1c8e8a00c-ff329d94");
//        headers.set("X-EBAY-API-SITE-ID", "0");
//        headers.set("X-EBAY-API-CALL-NAME", "GetSingleItem");
//        headers.set("X-EBAY-API-VERSION", "863");
//        headers.set("X-EBAY-API-REQUEST-ENCODING", "xml");
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String urn = "urn:ebay:apis:eBLBaseComponents";
        //String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <GetSingleItemRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\"><ItemID>313160996654</ItemID><IncludeSelector>Details</IncludeSelector></GetSingleItemRequest>";
        String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <GetSingleItemRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\"> <ItemID>313160996654</ItemID> </GetSingleItemRequest>";
        JSONObject soapDatainJsonObject = XML.toJSONObject(xmlString);
//        /String jsonPrettyPrintString = soapDatainJsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
//        System.out.println(xmlString);
//        System.out.println(soapDatainJsonObject.toString());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        RestTemplate restTemplate = new RestTemplate();
 
        //String shopping_url="https://open.api.ebay.com/shopping? callname=GetSingleItem & responseencoding=XML & appid=SanghunC-gkoo-PRD-1c8e8a00c-ff329d94 & siteid=0 & version=863 & ItemID=313160996654";
        ResponseEntity<String> response = restTemplate.exchange(shopping_url, 
                HttpMethod.GET, entity, String.class);
 
        String result = response.getBody();
        
        System.out.println(result);
        
        
        URL url = new URL(shopping_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(xmlString);
        wr.flush();
        wr.close();
        String responseStatus = con.getResponseMessage();
        System.out.println(responseStatus);
        BufferedReader in = new BufferedReader(new InputStreamReader(
        con.getInputStream()));
        String inputLine;
        StringBuffer responseEbay = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            responseEbay.append(inputLine);
        }
        in.close();
        
        System.out.println(responseEbay);
    }
}
