package ifanoxy.paname_uhc;

import java.util.Timer;
import java.util.TimerTask;

public class TimerJeu {
    private static int seconds = 0;
    private static WorldBorder worldBorder;

    public static void setWorldBorder(WorldBorder worldBorder) {
        //TimerJeu.worldBorder = worldBorder;
    }

    public int getSeconds() {
        return seconds;
    }

    public void startTimer() {
        Timer timer = new Timer();
        System.out.println("aoefzdhiu 12");

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("aoefzdhiu 12");
                seconds++;
                int displayMinutes = seconds / 60;
                int displaySeconds = seconds % 60;
                System.out.println("Temps: " + displayMinutes + " min " + displaySeconds + " sec");

                if (seconds == 20 * 60) { // 20 minutes
                    worldBorder.executeCommand();
                }
            }
        }, 3000, 1000); // delay 3 seconds, period 1 second
    }


    @Override
    public String toString() {
        int seconds = this.seconds % 60;
        int minutes = (int) Math.floor(this.seconds / 60);
        int hours = (int) Math.floor(minutes / 60);
        minutes = minutes % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
