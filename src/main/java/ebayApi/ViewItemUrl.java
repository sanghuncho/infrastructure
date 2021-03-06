package ebayApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

public class ViewItemUrl {
    //NOTE: Change this env to Environment.PRODUCTION to run this test in PRODUCTION
    private static final Environment EXECUTION_ENV = Environment.PRODUCTION;
    
    static final String POST_API = "https://open.api.ebay.com/shopping";
    private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope");
    
    private static final int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static void main( String[] args ) throws IOException {
        
//        List<String> itemNumberEbayList = Arrays.asList("164320800857");
//        
//        for (int i=0; i< itemNumberEbayList.size(); i++) {
//            retrieveProductData(itemNumberEbayList.get(i));
//        }
        
        reserveBid();
    }
    
    private static void reserveBid(){
        String endTime = "2020-08-09T06:22:53.000Z";
        ZonedDateTime zonedDateTimeEndAuction = ZonedDateTime.parse(endTime);
        Date auctionEndTime =  java.util.Date.from(zonedDateTimeEndAuction.toInstant());
        Date auctionEndTimedate = new Date(auctionEndTime.getTime());
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(auctionEndTimedate);
        System.out.println("입찰마감시간: " + format.format(auctionEndTimedate));
        
        ZonedDateTime zonedDateTimeReservation = zonedDateTimeEndAuction.minusMinutes(5L);
        Date reserveToBidTime =  java.util.Date.from(zonedDateTimeReservation.toInstant());
        System.out.println("입찰예약시간 독일: " + format.format(reserveToBidTime));
        
        ZonedDateTime zonedDateTimeReservationKorea = zonedDateTimeReservation.plusHours(8L);
        Date reserveToBidTimeKorea =  java.util.Date.from(zonedDateTimeReservationKorea.toInstant());
        System.out.println("입찰예약시간 한국서버: " +  format.format(reserveToBidTimeKorea));
        
    }
    
    private static void retrieveProductData(String itemNumberEbay) throws IOException {
        OAuth2Api oauth2Api = new OAuth2Api();
        CredentialUtil.load(new FileInputStream("C://Users/sanghuncho/git/infrastructure/src/java/resources/ebay-config.yaml"));
        OAuthResponse rep = oauth2Api.getApplicationToken(EXECUTION_ENV, authorizationScopesList);

        String shopping_url="https://open.api.ebay.com/shopping?callname=GetSingleItem&responseencoding=XML&appid=SanghunC-gkoo-PRD-1c8e8a00c-ff329d94&siteid=77&version=863&ItemID=" + itemNumberEbay + "&IncludeSelector=Details,ShippingCosts";
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
        //System.out.println(soapDatainJsonObject.toString());
        //System.out.println(soapDatainJsonObject.getJSONObject("GetSingleItemResponse"));
        JSONObject itemObjectJson = soapDatainJsonObject.getJSONObject("GetSingleItemResponse");
        System.out.println(itemObjectJson.getJSONObject("Item"));
        JSONObject itemObjectContentJson = itemObjectJson.getJSONObject("Item");
        System.out.println(itemObjectContentJson.getString("ViewItemURLForNaturalSearch"));
        System.out.println(itemObjectContentJson.getString("EndTime"));
        System.out.println(itemObjectContentJson.getJSONArray("PaymentMethods"));
    }
}
