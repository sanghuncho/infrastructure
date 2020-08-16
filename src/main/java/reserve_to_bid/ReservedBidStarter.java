package reserve_to_bid;

import java.text.ParseException;

public class ReservedBidStarter {
    public static void main(String[] args) throws ParseException {
        String endTimeAuctionGER = "2020-08-15T22:20:53.000Z";
        String itemNumberEbay = "203076918646";
        String userid = "m";
        int bidValue = 243;
        
        ScheduleBid scheduleBid = new ScheduleBid()
                .withEbayItemNumber(itemNumberEbay)
                .withUserid(userid)
                .withBidValue(bidValue)
                .withEndTimeAuctionGER(endTimeAuctionGER)
                .withReserveToBidTimeKorea()
                .withTimer()
                .build();
        ScheduleBidPlaner.scheduleBidList.add(scheduleBid);
    }
}
