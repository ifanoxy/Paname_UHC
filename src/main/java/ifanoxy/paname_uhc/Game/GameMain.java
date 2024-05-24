package ifanoxy.paname_uhc.Game;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.List;

public class GameMain {

    public void init(List<Player> players) {
        Server server = Bukkit.getServer();
        server.dispatchCommand(server.getConsoleSender(), "mv create UHC_GAME normal");
        for (Player p : players)
        {
            p.sendMessage("Téléportation en cours...");
            server.dispatchCommand(server.getConsoleSender(), String.format("mv tp %s UHC_GAME", p.getName()));
        }
    }
}
