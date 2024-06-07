package ifanoxy.paname_uhc.Roles;

import ifanoxy.paname_uhc.Paname_UHC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Leo {
    private int invisibleCount;
    private Plugin plugin;

    public void init(String pseudo, Paname_UHC plugin) {
        this.plugin = plugin;
        Player player = Bukkit.getPlayer(pseudo);
        this.invisibleCount = 0;
        this.checkInvisible(player);
    }
    
    private void checkInvisible(Player p) {
        final int[][] lastCoord = {getCoord(p)};
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
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
                setInvisible(invisibleCount >= 100, p);
            }
        }, 0, 2);
    }

    private void setInvisible(Boolean invisible, Player player) {
        if (invisible)
        {
            if (!player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                player.sendMessage("Vous êtes désormais invisible");
                PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false);
                player.addPotionEffect(effect);
            }
        } else {
            if (player.hasPotionEffect(PotionEffectType.INVISIBILITY))
            {
                player.sendMessage("Vous n'êtes plus invisible");
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
            }
        }
    }
    
    private int[] getCoord(Player p) {
        Location loc = p.getLocation();
        return new int[]{(int) loc.getX(), (int) loc.getY(), (int) loc.getZ()};
    }
}
