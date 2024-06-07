package ifanoxy.paname_uhc.Roles;

import ifanoxy.paname_uhc.Game.GameMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Empereur_SDF {
    private Player player;
    private GameMain game;

    public void init(String pseudo, GameMain GameMain) {
        this.player = Bukkit.getPlayer(pseudo);
        this.player.setHealthScale(30);
        this.game = GameMain;

        List<String> allyNames = new ArrayList();

        for (Map.Entry<String, String> entry : this.game.playersRoles.entrySet())
        {
            if (entry.getValue().contains("SDF") || entry.getValue().equals("Clotaire"))
            {
                allyNames.add(entry.getKey());
            }
        }

        player.sendMessage(
                "§eVoici la liste de vos alliées SDF:"
        );
        for (String name : allyNames)
        {
            player.sendMessage(String.format("§7> %s", name));
        }

        this.checkNight();
    }
    private void checkNight() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.game.plugin, new Runnable() {
            @Override
            public void run() {
                long time = Bukkit.getWorld("UHC_GAME").getTime();
                setStrenght(time < 12000, player);
            }
        }, 0, 200);
    }

    public void setStrenght(Boolean on, Player player)
    {
        if (on) {
            if (!player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
            {
                player.sendMessage("Le jour est levé, vous avez l'effet Force 1 !");
                PotionEffect effect2 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false);
                player.addPotionEffect(effect2);
            }
        } else {
            if (player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
            {
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            }
        }
    }
}