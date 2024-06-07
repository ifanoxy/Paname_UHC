package ifanoxy.paname_uhc;

import ifanoxy.paname_uhc.Game.GameMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Map;
import java.util.Objects;

public class Chat implements Listener {

    private final GameMain game;

    public Chat(GameMain gameMain) {
        this.game = gameMain;
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e ) {
        Player player = e.getPlayer();
        String message = e.getMessage();

        if (this.game.playersTeam == null)return;
        String Team = this.game.playersTeam.get(player.getName());


        if (Team == "SDF") {

            if (message.startsWith("!")) {
                e.setMessage(message.substring(1));
                return;
            };

            String selfRole = this.game.playersRoles.get(player.getName());
            if (message.startsWith("?") && (selfRole == "Empereur des SDF") || selfRole == "SDF croyant") {
                if (this.game.playersRoles.containsValue("Empereur des SDF")) {
                    for (Map.Entry<String, String> entry : this.game.playersTeam.entrySet()) {
                        if (entry.getValue() == "Empereur des SDF") {
                            Player player1 = this.game.server.getPlayer(entry.getKey());
                            if (player1 == null) break;
                            player1.sendMessage(String.format("§7[§eEmpereur/croyant§7]§r §k012345678§r §7§l> §6%s", message.substring(1)));
                        }
                    }
                    player.sendMessage(String.format("§7[§eEmpereur/croyant]§r §k012345678§r §7§l> §6%s", message.substring(1)));
                    e.setCancelled(true);
                    return;
                }
                e.setCancelled(true);
                return;
            };

            if (this.game.playersRoles.containsValue("Leo")) {
                for (Map.Entry<String, String> entry : this.game.playersTeam.entrySet()) {
                    if (entry.getValue() == "Leo") {
                        Player player1 = this.game.server.getPlayer(entry.getKey());
                        if (player1 == null) break;
                        player1.sendMessage(String.format("§7[§d%s§7]§r §k012345678§r §7§l> §a%s", Team, message));
                    }
                }
            }

            for (Map.Entry<String, String> entry : this.game.playersTeam.entrySet()) {
                if (entry.getValue() == Team) {
                    Player player1 = this.game.server.getPlayer(entry.getKey());
                    if (player1 == null) break;

                    player1.sendMessage(String.format("§7[§d%s§7]§r §k012345678§r §7§l> §a%s", Team, message));
                }
            }
            e.setCancelled(true);
        } else {
            e.setMessage(message);
        }
    }
}
