package opencv;

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

public class ScheduleBiding {

    public static void main(String [] args) throws ParseException {
    	List<Timer> timerList = new ArrayList<>();
    	Timer task1 = new Timer();
    	Timer task2 = new Timer();
    	Timer task3 = new Timer();

    	timerList.add(task1);
    	timerList.add(task2);
    	timerList.add(task3);

    	TimerTask timerTask1 = new TimerTask() {
            public void run() {
                System.out.println("runnable task1");
            }
        };
        
    	task1.schedule(timerTask1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-08-07 11:15:10"));
    	
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
