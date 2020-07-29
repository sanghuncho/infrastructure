package auctionApi;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
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
    private static final String IMAGE_DIR_HOME = "C:/Users/sanghuncho/Pictures/ebay/";
    private static final String IMAGE_DIR_OFFICE = "C:/Users/sanghuncho/Documents/Gkoo/ebay/";
    private static final String IMAGE_DIR = IMAGE_DIR_OFFICE;
    
    static final String BROWSE_API = "https://api.ebay.com/buy/browse/v1/item/get_item_by_legacy_id?legacy_item_id=";
    private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope");
    
    public static void main( String[] args ) throws IOException {

    	List<String> itemNumberEbayList = Arrays.asList("133466947335", "133466946597");
        int startItemNumberEbay = 200;
        
        for (int i=0; i< itemNumberEbayList.size(); i++) {
        	retrieveProductData(itemNumberEbayList.get(i), startItemNumberEbay+i);
        }
    }
    
    private static void retrieveProductData(String itemNumberEbay, int itemNumberGkoo) throws IOException {
        OAuth2Api oauth2Api = new OAuth2Api();
        CredentialUtil.load(new FileInputStream("C://Users/sanghuncho/git/infrastructure/src/java/resources/ebay-config.yaml"));
        OAuthResponse rep = oauth2Api.getApplicationToken(EXECUTION_ENV, authorizationScopesList);
        //System.out.println(rep.getAccessToken().get().getToken());
        
        String getItemId = BROWSE_API + itemNumberEbay;
        int itemNr = itemNumberGkoo;
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + rep.getAccessToken().get().getToken() );
        headers.set("X-EBAY-C-ENDUSERCTX", "contextualLocation=country=<2_character_country_code>,zip=<zip_code>" );
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
 
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        RestTemplate restTemplate = new RestTemplate();
 
        ResponseEntity<String> response = restTemplate.exchange(getItemId, 
                HttpMethod.GET, entity, String.class);
 
        String result = response.getBody();
        JSONObject myObject = new JSONObject(result);
        String title = myObject.getString("title");
        
        JSONObject priceObject = myObject.getJSONObject("price");
        String price = priceObject.getString("convertedFromValue");
        
        JSONObject sellerObject = myObject.getJSONObject("seller");
        String sellerUsername = sellerObject.getString("username");
        
        JSONObject urlObject = myObject.getJSONObject("image");
        String imageUrl = urlObject.getString("imageUrl");
        
        String brand = myObject.has("brand") ?  myObject.getString("brand") : "No brand";
        System.out.println('\n');
        System.out.println("Item Number GKoo:" + itemNumberGkoo);
        System.out.println(price);
        System.out.println(sellerUsername);
        System.out.println(title);
        System.out.println(brand);
        System.out.println(imageUrl);
        System.out.println('\n');
        //System.out.println(result);
        
        BufferedImage image =null;
        String imageAddr = null;
        String imageName = "";
        try{
            URL url =new URL(imageUrl);
            imageName = String.valueOf(itemNr) + ".jpg";
            image = ImageIO.read(url);
            imageAddr = IMAGE_DIR + imageName;
            ImageIO.write(image, "jpg", new File(imageAddr));
        } catch(IOException e){
            e.printStackTrace();
        }
        
        File input = new File(IMAGE_DIR + imageName);
        BufferedImage originalImage = ImageIO.read(input);
        
        BufferedImage resized = new BufferedImage(110, 40, originalImage.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, 110, 40, 0, 0, originalImage.getWidth(),
                originalImage.getHeight(), null);
        g.dispose();
        
        //BufferedImage resized = resize(imageResize, 280, 100);
        String resizedImageAddr = IMAGE_DIR + String.valueOf(itemNr) + "_resize" + ".jpg";
        File output = new File(resizedImageAddr);
        ImageIO.write(resized, "jpg", output);
    }
}
