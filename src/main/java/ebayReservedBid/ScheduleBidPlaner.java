package ebayReservedBid;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * Time to bid in Korea
 * 
 * @author sanghuncho
 *
 */
public class ScheduleBidPlaner {
	private static final Logger LOGGER = LogManager.getLogger();
	public static final long HALF_INTERVAL = 3L;
    public static List<ScheduleBid> scheduleBidList = new ArrayList<>();
    private static final long NORMAL_JETLAG = 8L;
    private static final long SUMMERTIME_JETLAG = 7L;
    
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
    
    public static Date getReservedBidTimeKorea(ZonedDateTime zonedDateTimeEndAuction) {
        ZonedDateTime beforeZonedDateTimeReservation = zonedDateTimeEndAuction.minusMinutes(HALF_INTERVAL);
        ZonedDateTime afterZonedDateTimeReservation = zonedDateTimeEndAuction.plusMinutes(HALF_INTERVAL);
        
        while(alreadyExistBid(beforeZonedDateTimeReservation, afterZonedDateTimeReservation)) {
            beforeZonedDateTimeReservation = zonedDateTimeEndAuction.minusMinutes(HALF_INTERVAL);
            afterZonedDateTimeReservation = zonedDateTimeEndAuction.plusMinutes(HALF_INTERVAL);
        }
        
        ZonedDateTime zonedDateTimeReservation = beforeZonedDateTimeReservation.plusHours(NORMAL_JETLAG);
        Date reserveToBidTimeKorea =  java.util.Date.from(zonedDateTimeReservation.toInstant());
        return reserveToBidTimeKorea;
    }
    
    public static boolean alreadyExistBid(ZonedDateTime beforeZonedDateTimeReservation, ZonedDateTime afterZonedDateTimeReservation) {
        Date startReserveToBidTimedate =  java.util.Date.from(beforeZonedDateTimeReservation.toInstant());
        Date endReserveToBidTimedate =  java.util.Date.from(afterZonedDateTimeReservation.toInstant());
        boolean result = scheduleBidList
                .stream()
                .parallel().anyMatch(bid -> startReserveToBidTimedate.after(bid.getReserveToBidTimeKorea()) &&
                        endReserveToBidTimedate.before(bid.getReserveToBidTimeKorea()));
        
        return result;
    }
}
