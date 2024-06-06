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
    private Objective objective;


    public void init(GameMain game) {
        this.game = game;
        this.manager = Bukkit.getScoreboardManager();

        for (Player player : this.game.playersList) {
            Scoreboard board = manager.getNewScoreboard();
            player.setScoreboard(board);
            this.setupScoreBoard(board);
            this.updatemainScores(board, player);
        }
    }


    public void setupScoreBoard(Scoreboard board) {
        this.objective = board.registerNewObjective("serveur_info", "dummy");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName(ChatColor.GREEN + "Serveur Paname UHC");

    }
    public void updatemainScores(Scoreboard board, Player player) {

        for (String str : board.getEntries()) {
            board.resetScores(str);
        }
        Location location = player.getLocation();

        this.objective.getScore(String.format("     ")).setScore(12);
        this.objective.getScore(String.format(" > Nombre de Joueur %s", game.playersList.size())).setScore(11);
        this.objective.getScore(String.format("    ")).setScore(10);
        this.objective.getScore(String.format(" > World Border: %s x %s", Math.round((float) this.game.wb.size /2), Math.round((float) this.game.wb.size /2))).setScore(9);
        this.objective.getScore(String.format("   ")).setScore(8);
        this.objective.getScore(String.format(" > Kill: %s", game.kills.get(player.getName()))).setScore(7);
        this.objective.getScore(String.format("  ")).setScore(6);
        this.objective.getScore(String.format(" > x: %s y: %s z: %s", (int)location.getX(), (int)location.getY(), (int)location.getZ())).setScore(5);
        this.objective.getScore(String.format(" ")).setScore(4);
        this.objective.getScore(String.format(" > Timer %s", game.timer.toString())).setScore(3);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updatemainScores(board, player);
            }
        }, 80);
    }
}
