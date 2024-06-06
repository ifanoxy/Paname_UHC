package ifanoxy.paname_uhc.Roles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Timer;
import java.util.TimerTask;

public class Sidney {
    private Player player;

    public void init(String pseudo) {
        this.player = Bukkit.getPlayer(pseudo);
        PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0);
        player.addPotionEffect(effect);
        /*
        PotionEffect effect1 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0);
        player.addPotionEffect(effect1);
         */

        this.checkNight();
    }
    private void checkNight() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long time = Bukkit.getWorld("UHC_GAME").getTime();
                if (time > 12000 && time < 24000) {
                    PotionEffect effect2 = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0);
                    player.addPotionEffect(effect2);
                } else {
                    if (player.hasPotionEffect(PotionEffectType.SPEED))
                    {
                        player.removePotionEffect(PotionEffectType.SPEED);
                    }
                }
            }
        }, 3000, 1000);
    }
}