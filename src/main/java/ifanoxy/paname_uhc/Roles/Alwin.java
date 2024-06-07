package ifanoxy.paname_uhc.Roles;

import ifanoxy.paname_uhc.Paname_UHC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Alwin {
    public void init(String pseudo, Paname_UHC plugin) {
        Player player = Bukkit.getPlayer(pseudo);
        PotionEffect effect = new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0, false, false);
        player.addPotionEffect(effect);
        player.setHealthScale(10);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                player.setHealthScale(24);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
            }
        },20 * 60 * 60);
    }
}
