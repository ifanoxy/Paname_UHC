package ifanoxy.paname_uhc.Game;

import ifanoxy.paname_uhc.TimerJeu;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameMain {

    public TimerJeu timer;
    public List<Player> playersList;
    public ScoreboardGame scoreboard;
    private CommandSender sender;
    private Server server;
    public HashMap<String, String> playersRoles;

    public void init(List<Player> players) {
        this.playersList = players;
        this.scoreboard = new ScoreboardGame();

        this.server = Bukkit.getServer();
        this.sender = this.server.getConsoleSender();

        this.distributionsDesRoles();

        server.dispatchCommand(this.sender, "mv create UHC_GAME normal");

        System.out.println("Lancement du timer");
        this.timer = new TimerJeu();

        for (Player p : players)
        {
            p.sendMessage("Téléportation en cours...");
            this.server.dispatchCommand(this.sender, String.format("mv tp %s UHC_GAME", p.getName()));
            this.server.dispatchCommand(this.sender, "mv confirm");

        }
    }

    public void distributionsDesRoles() {
        String[] teams = new String[]{"Sydney", "Famille de Jafar", "Français", "SDF"};
        this.playersRoles = new HashMap<String, String>();

        for (final Player player : this.playersList) {
            String teamName = teams[new Random().nextInt(4)];

            this.scoreboard.addToTeam(player, teamName);
            this.playersRoles.put(player.getName(), teamName);
        }
    }
}
