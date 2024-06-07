package ifanoxy.paname_uhc;

import ifanoxy.paname_uhc.Game.GameMain;
import ifanoxy.paname_uhc.Roles.Sidney;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class PlayerDeath implements Listener {


    private final GameMain game;

    public PlayerDeath(GameMain gameMain) {
        this.game = gameMain;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        String message = "§4" + player.getName() + "est mort. Force à lui.";
        Bukkit.broadcastMessage(message);
        this.game.playersList.remove(player);
        this.game.playersRoles.remove(player.getName());
        player.setGameMode(GameMode.SPECTATOR);
        this.game.server.dispatchCommand(
                this.game.sender,
                String.format(
                        "lp user %s group set spectateur",
                        player.getName()
                ));
        event.setDeathMessage(message);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.game.playersList.remove(player);
        this.game.playersRoles.remove(player.getName());
        this.game.server.dispatchCommand(
                this.game.sender,
                String.format(
                        "lp user %s group set lobby",
                        player.getName()
                ));

    }
}
