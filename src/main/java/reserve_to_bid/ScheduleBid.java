package reserve_to_bid;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleBid {
    public static List<ScheduleBid> scheduleBidList = new ArrayList<>();

    private String ebayItemNumber;
    private String userid;
    private double bidValue;
    private String reserveToBidTimeKorea;
    private Timer timer;
    //private TimerTask timerTask;
    
    public ScheduleBid(){
        
    }
    
    public ScheduleBid(String ebayItemNumber, String userid, double bidValue, String reserveToBidTimeKorea, Timer timer){
        this.ebayItemNumber = ebayItemNumber;
        this.userid = userid;
        this.bidValue = bidValue;
        this.reserveToBidTimeKorea = reserveToBidTimeKorea;
        this.timer = timer;
    }
    
    public ScheduleBid setTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                System.out.println("starts autoBidding!");
            }
        };
        try {
            timer.schedule(timerTask, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reserveToBidTimeKorea));
        } catch (ParseException e) {
            //logger
        }
        return this;
    }
    
    public ScheduleBid setEbayItemNumber(String itemNumber) {
        this.ebayItemNumber = itemNumber;
        return this;
    }
    
    public ScheduleBid setUserid(String userid) {
        this.userid = userid;
        return this;
    }
    
    public ScheduleBid setBidValue(double bidValue) {
        this.bidValue = bidValue;
        return this;
    }
    
    public ScheduleBid setReserveToBidTimeKorea(String reserveToBidTimeKorea) {
        this.reserveToBidTimeKorea = reserveToBidTimeKorea;
        return this;
    }
    
    public ScheduleBid build() {
        ScheduleBid scheduleBid = new ScheduleBid(ebayItemNumber, userid, bidValue, reserveToBidTimeKorea, timer);
        scheduleBidList.add(scheduleBid);
        return scheduleBid;
    }

    public static void main(String [] args) throws ParseException {
    	
        
    }
    
    private static void testScheduleBid() throws ParseException {
        List<Timer> timerList = new ArrayList<>();
        Timer timer1 = new Timer();
        timerList.add(timer1);
        TimerTask timerTask1 = new TimerTask() {
            public void run() {
                System.out.println("runnable task1");
                //autoBidding starts
            }
        };
        timer1.schedule(timerTask1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-08-07 11:15:10"));


        Timer task2 = new Timer();
        Timer task3 = new Timer();
        
        timerList.add(task2);
        timerList.add(task3);
        
        Date date = new Date(timerTask1.scheduledExecutionTime());
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        System.out.println(format.format(date));
        
        task2.schedule(new TimerTask() {
            public void run() {
                System.out.println("runnable task2");
            }
        }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-08-07 11:15:30"));
        
        task3.schedule(new TimerTask() {
            public void run() {
                timerList.get(1).cancel();
                System.out.println("cancel task2");
            }
        }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-08-07 11:15:20"));
    }
}
