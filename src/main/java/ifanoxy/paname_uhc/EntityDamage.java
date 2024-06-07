package ifanoxy.paname_uhc;

import ifanoxy.paname_uhc.Game.GameMain;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;
import java.util.Random;

public class EntityDamage implements Listener {
    private final GameMain game;

    public EntityDamage(GameMain gameMain) {
        this.game = gameMain;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (this.game.timer == null) {
            e.setCancelled(true);
            return;
        } else if (this.game.timer.getSeconds() <= 30) {
            e.setCancelled(true);
            return;
        }

        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL))
            {
                String poulet = this.game.playersPoulets.get(p.getName());

                if (poulet == null)return;

                if (poulet == "Poulet Mi-Cuit")e.setCancelled(true);
                return;
            }

            if (e.getDamager() instanceof  Player)
            {
                Entity killer = e.getDamager();
                String role = this.game.playersRoles.get(killer.getName());
                String targetrole = this.game.playersRoles.get(p.getName());

                String poulet = this.game.playersPoulets.get(p.getName());
                String pouletTarget = this.game.playersPoulets.get(killer.getName());

                if (pouletTarget == "Poulet Bien Cuit") {
                    e.setDamage(e.getDamage()*0.92);
                }

                if (poulet == "Poulet Cuit") {
                    e.setDamage(e.getDamage() * 1.08);
                }

                if (role == null || targetrole == null)return;

                if (targetrole == "SDF esquive")
                {
                    final int rand = new Random().nextInt(15);
                    if (rand == 0)
                    {
                        p.sendMessage("tu as veski mon chef");
                        e.setCancelled(true);
                        return;
                    }
                }

                if (role == "Sidney" && targetrole.contains("SDF"))
                {
                    p.damage(e.getDamage() + 3);
                    e.setCancelled(true);
                }
            }

        }
    }
}
