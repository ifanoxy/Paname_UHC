package ifanoxy.paname_uhc;

import ifanoxy.paname_uhc.Game.GameMain;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class EntityDamage implements Listener {
    private final GameMain game;

    public EntityDamage(GameMain gameMain) {
        this.game = gameMain;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            Entity executor = Objects.requireNonNull(p.getLastDamageCause()).getEntity();
            if (executor instanceof Player) {
                String role = this.game.playersRoles.get(executor.getName());
                String targetrole = this.game.playersRoles.get(p.getName());
                if (Objects.equals(role, "sidney") && targetrole.contains("sdf"))
                {
                    System.out.println("azoihdoapzjdpoajzd");
                    p.damage(e.getDamage() * 1.3 + (1.3-1)/0.3);
                }
            }
        }
    }
}
