package ifanoxy.paname_uhc.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class ScoreboardGame {

    private ScoreboardManager manager;
    private Scoreboard board;
    public HashMap<String, Team> teams;

    public void init(GameMain game) {
        this.game = game;
        this.manager = Bukkit.getScoreboardManager();
        this.board = manager.getNewScoreboard();
        this.teams = new HashMap<String, Team>();
        this.loadTeams();
    }

    private void loadTeams() {
        this.teams = new HashMap<String, Team>();

        Team Sydney = this.board.registerNewTeam("Sydney");
        this.teams.put("Sydney", Sydney);

        Team Jafar = this.board.registerNewTeam("Famille de Jafar");
        this.teams.put("Famille de Jafar", Jafar);

        Team Francais = this.board.registerNewTeam("Français");
        this.teams.put("Français", Francais);

        Team SDF = this.board.registerNewTeam("SDF");
        this.teams.put("SDF", SDF);

        this.setDefaultParamTeams();

        this.board.registerNewTeam("player_online");
        this.board.registerNewTeam("timer");
    }

    private void setDefaultParamTeams() {
        this.teams.forEach((name, team) -> {
            team.setAllowFriendlyFire(false);
        });
    }

    public boolean addToTeam(Player player, String team) {
        Team teamAdded = this.teams.get(team);
        teamAdded.addEntry(player.getName());
        return true;
    }
}
