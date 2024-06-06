package ifanoxy.paname_uhc.Commands;

import ifanoxy.paname_uhc.Game.GameMain;
import ifanoxy.paname_uhc.Paname_UHC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class StartCommand implements CommandExecutor {
    String name;
    private Paname_UHC plugin;

    public StartCommand(Paname_UHC panameUhc) {
        this.plugin = panameUhc;
    }
    @Override
    public boolean onCommand(@NonNull CommandSender sender,@NonNull Command command,@NonNull String label,@NonNull String[] args)
    {
        this.name = "start";
        if (sender instanceof Player)
        {
            final List<Player> OnlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

            Bukkit.broadcastMessage(String.format("Lancement de l'UHC avec %d personnes !", OnlinePlayers.size()));

            GameMain jeu = new GameMain();
            jeu.init(OnlinePlayers, this.plugin);
        }
        return false;
    }
}
