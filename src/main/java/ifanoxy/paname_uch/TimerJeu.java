package ifanoxy.paname_uch;

import java.util.Timer;
import java.util.TimerTask;

public class TimerJeu  {
    public static void main(String[] args){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            int minutes = 0;

            @Override
            public void run() {
                minutes++;
                System.out.println("Temps:" + minutes + "min");
                timer.schedule(this, 60000);
            }
        }, 3000);
    }
}
