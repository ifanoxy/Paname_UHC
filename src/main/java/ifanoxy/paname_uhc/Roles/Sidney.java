package ifanoxy.paname_uhc.Roles;

import ifanoxy.paname_uhc.Paname_UHC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Sidney {
    private Player player;
    private Paname_UHC plugin;

    public void init(String pseudo, Paname_UHC plugin) {
        this.player = Bukkit.getPlayer(pseudo);
        PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, false, false);
        player.addPotionEffect(effect);
        this.plugin = plugin;

        this.checkNight();
    }
    private void checkNight() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                long time = Bukkit.getWorld("UHC_GAME").getTime();
                setSpeed(time > 12000 && time < 24000, player);
            }
        }, 0, 200);
    }

    public void setSpeed(Boolean on, Player player)
    {
        if (on) {
            if (!player.hasPotionEffect(PotionEffectType.SPEED))
            {
                PotionEffect effect2 = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false);
                player.addPotionEffect(effect2);
            }
        } else {
            if (player.hasPotionEffect(PotionEffectType.SPEED))
            {
                player.removePotionEffect(PotionEffectType.SPEED);
            }
        }
    }
}