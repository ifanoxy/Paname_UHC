package ifanoxy.paname_uhc.Roles;

import ifanoxy.paname_uhc.Game.GameMain;
import ifanoxy.paname_uhc.Paname_UHC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;


public class Nacima {

    private Player player;
    private GameMain game;

    public void init(String pseudo, GameMain gameMain) {
        this.player = Bukkit.getPlayer(pseudo);
        this.game = gameMain;
        this.checkAround();
    }

    private void checkAround() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.game.plugin, new Runnable() {
            @Override
            public void run() {
                Collection<Entity> Entities = player.getNearbyEntities(15, 15, 15);
                for (Entity entity : Entities)
                {
                    if (entity instanceof Player)
                    {
                        setResistance(player);
                    }
                }
            }
        }, 0, 60);
    }

    private void setResistance(Player p) {
        String Team = this.game.playersTeam.get(p.getName());
        System.out.println(Team);
        if (Team == "Famille de Jafar")
        {
            PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5000, 0, false, false);
            p.addPotionEffect(effect);
        }
    }
}
