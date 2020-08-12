package reserve_to_bid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScheduleBidPlaner {
	private static final Logger LOGGER = LogManager.getLogger();
    public static List<ScheduleBid> scheduleBidList = new ArrayList<>();
    
    public void cancelScheduleBid(String ebayItemNumber, String userid) {
    	Objects.nonNull(ebayItemNumber);
    	Objects.nonNull(userid);
    	Optional<ScheduleBid> result = scheduleBidList
    			.stream()
    			.parallel()
    			.filter(bid -> bid.getEbayItemNumber() == ebayItemNumber && bid.getUserid() == userid).findAny();
    	
    	result.ifPresentOrElse(
    			resullt -> result.get().getTimer().cancel(), 
    			() -> LOGGER.error("Scheduled bid is not found and not canceled - EbayItemNumber: " + ebayItemNumber + "/" + "userid: " + userid)
    	);
    }
}
