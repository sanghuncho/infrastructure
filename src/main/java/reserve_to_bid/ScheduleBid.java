package reserve_to_bid;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScheduleBid {
    private static final Logger LOGGER = LogManager.getLogger();
    private String ebayItemNumber;
    private String userid;
    private double bidValue;
    private Date reserveToBidTimeKorea;
    private String endTimeAuctionGER;
    private Timer timer;
    
    public ScheduleBid(){
        
    }
    
    public ScheduleBid(String ebayItemNumber, String userid, double bidValue, String endTimeAuctionGER, Timer timer){
        this.ebayItemNumber = ebayItemNumber;
        this.userid = userid;
        this.bidValue = bidValue;
        this.endTimeAuctionGER = endTimeAuctionGER;
        this.timer = timer;
    }
    
    public ScheduleBid setTimer() throws ParseException {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                System.out.println("starts autoBidding!");
            }
        };

        reserveToBidTimeKorea = reserveToBidTimekorea(endTimeAuctionGER);
        timer.schedule(timerTask, reserveToBidTimeKorea);
        
        return this;
    }
    
    public ScheduleBid setEbayItemNumber(String itemNumber) {
        this.ebayItemNumber = itemNumber;
        return this;
    }
    
    public String getEbayItemNumber() {
        return this.ebayItemNumber;
    }
    
    public ScheduleBid setUserid(String userid) {
        this.userid = userid;
        return this;
    }
    
    public String getUserid() {
        return this.userid;
    }
    
    public Timer getTimer() {
        return this.timer;
    }
    
    public Date getReserveToBidTimeKorea() {
        return this.reserveToBidTimeKorea;
    }
    
    public ScheduleBid setBidValue(double bidValue) {
        this.bidValue = bidValue;
        return this;
    }
    
    public ScheduleBid setEndTimeAuctionGER(String endTimeAuctionGER) {
        this.endTimeAuctionGER = endTimeAuctionGER;
        return this;
    }
    
    public ScheduleBid setReserveToBidTimeKorea(Date reserveToBidTimeKorea) {
        this.reserveToBidTimeKorea = reserveToBidTimeKorea;
        return this;
    }
    
    public ScheduleBid build() {
        ScheduleBid scheduleBid = new ScheduleBid(ebayItemNumber, userid, bidValue, endTimeAuctionGER, timer);
        ScheduleBidPlaner.scheduleBidList.add(scheduleBid);
        return scheduleBid;
    }

    public static void main(String [] args) throws ParseException {
//        String endTime = "2020-08-15T04:22:53.000Z";
//        ZonedDateTime zonedDateTimeEndAuction = ZonedDateTime.parse(endTime);
//        Date auctionEndTime =  java.util.Date.from(zonedDateTimeEndAuction.toInstant());
//        System.out.println(auctionEndTime);
//        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(format.format(auctionEndTime));
        testScheduleBid();
    }

    private static Date reserveToBidTimekorea(String endtimeAuctionGER){
        //String endTime = "2020-08-09T06:22:53.000Z";
        Objects.nonNull(endtimeAuctionGER);
        ZonedDateTime zonedDateTimeEndAuction = ZonedDateTime.parse(endtimeAuctionGER);
//        Date auctionEndTime =  java.util.Date.from(zonedDateTimeEndAuction.toInstant());
//        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("입찰마감시간 독일: " + format.format(auctionEndTime));
        
        //before 5 minutes
//        ZonedDateTime zonedDateTimeReservation = zonedDateTimeEndAuction.minusMinutes(5L);
//        Date reserveToBidTime =  java.util.Date.from(zonedDateTimeReservation.toInstant());
//        System.out.println("입찰예약시간 독일: " + format.format(reserveToBidTime));
//
//        ZonedDateTime zonedDateTimeReservationKorea = zonedDateTimeReservation.plusHours(8L);
//        Date reserveToBidTimeKorea =  java.util.Date.from(zonedDateTimeReservationKorea.toInstant());
//        System.out.println("입찰예약시간 한국서버: " +  format.format(reserveToBidTimeKorea));
        Date result = ScheduleBidPlaner.getReservedBidTimeKorea(zonedDateTimeEndAuction);
        return result;
    }

    private static void testScheduleBid() throws ParseException {
        List<Timer> timerList = new ArrayList<>();
        Timer timer1 = new Timer();
        timerList.add(timer1);
        TimerTask timerTask1 = new TimerTask() {
            public void run() {
                System.out.println("runnable task1");
                //autoBidding algorithmus starts
            }
        };
        String endTime = "2020-08-15T05:02:53.000Z";
        ZonedDateTime zonedDateTimeEndAuction = ZonedDateTime.parse(endTime);
        Date auctionEndTime =  java.util.Date.from(zonedDateTimeEndAuction.toInstant());
        System.out.println(auctionEndTime);
        
        timer1.schedule(timerTask1, auctionEndTime);
        //timer1.schedule(timerTask1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-08-07 11:15:10"));

        Timer task2 = new Timer();
        Timer task3 = new Timer();
        
//        timerList.add(task2);
//        timerList.add(task3);
        
//        Date date = new Date(timerTask1.scheduledExecutionTime());
//        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
//        System.out.println(format.format(date));
        
        task2.schedule(new TimerTask() {
            public void run() {
                System.out.println("runnable task2");
            }
        }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-08-07 11:15:30"));
        
        task3.schedule(new TimerTask() {
            public void run() {
//                timerList.get(1).cancel();
                System.out.println("cancel task2");
            }
        }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-08-07 11:15:20"));
    }
}
