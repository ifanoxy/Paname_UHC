package ifanoxy.paname_uhc;

import ifanoxy.paname_uhc.Game.GameMain;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDeath implements Listener {


    private final GameMain game;

    public PlayerDeath(GameMain gameMain) {
        this.game = gameMain;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Entity mob = event.getEntity();
        if (mob.getLastDamageCause() instanceof Player)
        {
            int lastKill = this.game.kills.get(mob.getName());
            this.game.kills.put(mob.getName(), lastKill + 1);
        }
        Player player = event.getEntity();
        String message = "§4" + player.getName() + "est mort. Force à lui.";
        Bukkit.broadcastMessage(message);
        this.game.playersList.remove(player);
        this.game.playersRoles.remove(player);
        player.setGameMode(GameMode.SPECTATOR);
        this.game.server.dispatchCommand(
                this.game.sender,
                String.format(
                        "lp user %s group set spectateur",
                        player.getName()
                ));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.game.playersList.remove(player);
        this.game.playersRoles.remove(player);
        this.game.server.dispatchCommand(
                this.game.sender,
                String.format(
                        "lp user %s group set lobby",
                        player.getName()
                ));

    }
}
