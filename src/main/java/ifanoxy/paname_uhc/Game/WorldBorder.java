package ifanoxy.paname_uhc.Game;

import ifanoxy.paname_uhc.TimerJeu;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldBorder extends JavaPlugin {

    @Override
    public void onEnable(){
        CommandSender sender = Bukkit.getConsoleSender();
        Bukkit.dispatchCommand(sender, "worldborder set 2000");
        TimerJeu.setWorldBorder(this);
    }

    public void executeCommand() {
        CommandSender sender = Bukkit.getConsoleSender();
        Bukkit.dispatchCommand(sender, "worldborder add -3 10");
    }
}
