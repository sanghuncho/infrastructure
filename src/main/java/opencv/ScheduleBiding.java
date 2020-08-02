package opencv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleBiding {

    public static void main(String [] args) throws ParseException {
        Timer t=new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                System.out.println("runnable");
            }
        }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-08-02 06:32:50"));
    }
}
