package ifanoxy.paname_uhc.Roles;

import ifanoxy.paname_uhc.Paname_UHC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Voleur_SDF {
    private int timeCount;
    private Plugin plugin;

    public void init(String pseudo, Paname_UHC plugin) {
        this.plugin = plugin;
        Player player = Bukkit.getPlayer(pseudo);
        PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false);
        player.addPotionEffect(effect);
        this.timeCount = 0;
        this.checkAround(player);
    }
    
    private void checkAround(Player p) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                if (p.getNearbyEntities(30, 30, 30).size() == 0)
                {
                    timeCount = 0;
                } else {
                    timeCount += 1;
                }
                if (timeCount >= 3000)
                    giveApple(p);
            }
        }, 0, 10);
    }

    private void giveApple(Player player) {
        timeCount = 0;
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
    }
}
