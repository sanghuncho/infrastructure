package reserve_to_bid;

import java.io.IOException;

public class BidNowStarter {
        public static void main(String[] args) throws IOException {
            String itemNumberEbay = "203076918646";
            String userid = "m";
            int bidValue = 243;
            AutoBidingApp autoBidingApp = new AutoBidingApp(itemNumberEbay,  bidValue);
            autoBidingApp.run();
    }
}
