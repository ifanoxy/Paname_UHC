package ifanoxy.paname_uhc;

import ifanoxy.paname_uhc.Game.GameMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Objects;

public class Mange implements Listener {

    private final GameMain game;

    public Mange(GameMain gameMain) {
        this.game = gameMain;
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        long time = Objects.requireNonNull(Bukkit.getWorld("UHC_GAME")).getTime();
        if (this.game.ramadan && ( time < 12000) )
        {
            String role = this.game.playersRoles.get(event.getPlayer().getName());
            if (role == "Sidney" || role == "Nacima" || role == "Jafar")
            {
                Player player = Bukkit.getPlayer(event.getPlayer().getName());
                if (player != null)
                {
                    player.damage(1);
                    player.sendMessage("§cC'est HARAM mon gars !!");
                    event.setCancelled(true);
                }
            }
        }
    }
}
