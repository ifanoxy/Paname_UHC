package ifanoxy.paname_uhc.Roles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Empereur_SDF {
    public void init(String pseudo) {
        Player player = Bukkit.getPlayer(pseudo);
        PotionEffect effect = new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 1);
        player.addPotionEffect(effect);
        PotionEffect effect1 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0);
        player.addPotionEffect(effect1);
    }
}