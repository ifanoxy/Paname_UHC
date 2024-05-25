package ifanoxy.paname_uhc.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class ScoreboardGame {

    private ScoreboardManager manager;
    private Scoreboard board;
    public HashMap<String, Team> teams;

    public void init() {
        this.manager = Bukkit.getScoreboardManager();
        this.board = manager.getNewScoreboard();
        this.teams = new HashMap<String, Team>();
        this.loadTeams();
    }

    private void loadTeams() {
        Team Sydney = this.board.registerNewTeam("Sydney");
        Sydney.setColor(ChatColor.AQUA);
        Sydney.setPrefix("[Sydney]");
        this.teams.put("Sydney", Sydney);

        Team Jafar = this.board.registerNewTeam("Famille de Jafar");
        Jafar.setColor(ChatColor.GOLD);
        Jafar.setPrefix("[Famille de Jafar]");
        this.teams.put("Famille de Jafar", Sydney);

        Team Francais = this.board.registerNewTeam("Français");
        Francais.setColor(ChatColor.BLUE);
        Francais.setPrefix("[Français]");
        this.teams.put("Français", Sydney);

        Team SDF = this.board.registerNewTeam("SDF");
        SDF.setColor(ChatColor.DARK_GRAY);
        SDF.setPrefix("[SDF]");
        this.teams.put("SDF", Sydney);

        this.setDefaultParamTeams();
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
