package ifanoxy.paname_uhc.Roles;

import ifanoxy.paname_uhc.Paname_UHC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class Sale_SDF {
    private Plugin plugin;

    public void init(String pseudo, Paname_UHC plugin) {
        this.plugin = plugin;
        Player player = Bukkit.getPlayer(pseudo);
        player.setHealthScale(16);
        this.checkAround(player);
    }
    
    private void checkAround(Player p) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                givePotion(p);
            }
        }, 0, 20 * 60 * 15);
    }

    private void givePotion(Player player) {
        Potion potion = new Potion(PotionType.INSTANT_DAMAGE);
        potion.setSplash(true);
        player.getInventory().addItem(potion.toItemStack(1));
    }
}
