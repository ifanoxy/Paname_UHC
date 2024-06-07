package ifanoxy.paname_uhc.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class ScoreboardGame {

    private ScoreboardManager manager;
    private GameMain game;


    public void init(GameMain game) {
        this.game = game;
        this.manager = Bukkit.getScoreboardManager();

        for (Player player : this.game.playersList) {
            Scoreboard board = manager.getNewScoreboard();
            player.setScoreboard(board);
            Objective objective = board.registerNewObjective(player.getName(), "dummy");
            this.setupScoreBoard(board, objective);
            this.updatemainScores(board, player, objective);
        }
    }


    public void setupScoreBoard(Scoreboard board, Objective objective) {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GREEN + "Serveur Paname UHC");

    }
    public void updatemainScores(Scoreboard board, Player player, Objective objective) {

        Location location = player.getLocation();

        for (String str : board.getEntries()) {
            board.resetScores(str);
        }

        objective.getScore(String.format("     ")).setScore(12);
        objective.getScore(String.format(" > Nombre de Joueur %s", game.playersList.size())).setScore(11);
        objective.getScore(String.format("    ")).setScore(10);
        objective.getScore(String.format(" > World Border: %s x %s", Math.round((float) this.game.wb.size /2), Math.round((float) this.game.wb.size /2))).setScore(9);
        objective.getScore(String.format("   ")).setScore(8);
        objective.getScore(String.format(" > Kill: %s", game.kills.get(player.getName()))).setScore(7);
        objective.getScore(String.format("  ")).setScore(6);
        objective.getScore(String.format(" > x: %s y: %s z: %s", (int)location.getX(), (int)location.getY(), (int)location.getZ())).setScore(5);
        objective.getScore(String.format(" ")).setScore(4);
        objective.getScore(String.format(" > Timer %s", game.timer.toString())).setScore(3);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updatemainScores(board, player, objective);
            }
        }, 80);
    }
}
