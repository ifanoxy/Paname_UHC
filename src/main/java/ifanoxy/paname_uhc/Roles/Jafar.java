package ifanoxy.paname_uhc.Roles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Jafar extends JavaPlugin {
    @Override
    public void onEnable() {
        Player player = Bukkit.getPlayer("Pseudo");
        PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1);
        player.addPotionEffect(effect);
    }
}
