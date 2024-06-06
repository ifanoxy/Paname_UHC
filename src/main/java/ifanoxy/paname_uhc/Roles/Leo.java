package ifanoxy.paname_uhc.Roles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Leo {
    private int invisibleCount;

    public void init(String pseudo) {
        Player player = Bukkit.getPlayer(pseudo);
        this.invisibleCount = 0;
        this.checkInvisible(player);
    }
    
    private void checkInvisible(Player p) {
        final int[][] lastCoord = {getCoord(p)};
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int[] newCoord = getCoord(p);
                if (Arrays.equals(lastCoord[0], newCoord))
                {
                    invisibleCount += 1;
                } else {
                    invisibleCount = 0;
                    lastCoord[0] = newCoord;
                }
                if (invisibleCount >= 10) {
                    p.setInvisible(true);
                } else {
                    if (p.isInvisible())
                    {
                        p.setInvisible(false);
                    }
                }
            }
        }, 0, 1000);
    }
    
    private int[] getCoord(Player p) {
        Location loc = p.getLocation();
        return new int[]{(int) loc.getX(), (int) loc.getY(), (int) loc.getZ()};
    }
}
