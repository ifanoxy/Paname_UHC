package ifanoxy.paname_uch.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class StartCommand implements CommandExecutor {
    String name;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        this.name = "start";
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            final Collection<? extends Player> OnlinePlayer = Bukkit.getOnlinePlayers();


        }
        return false;
    }
}
