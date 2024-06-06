package ifanoxy.paname_uhc.Commands;

import ifanoxy.paname_uhc.Roles.Empereur_SDF;
import ifanoxy.paname_uhc.Roles.Jafar;
import ifanoxy.paname_uhc.Roles.Sidney;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class SetRole implements CommandExecutor {
    String name;
    public String[] roles;

    public void Main() {

    }
    @Override
    public boolean onCommand(@NonNull CommandSender sender,@NonNull Command command,@NonNull String label,@NonNull String[] args)
    {
        this.name = "setrole";
        this.roles = new String[]{"Jafar", "Sidney", "Empereur"};
        final String playerName = args.length == 1 ? args[0] : null;
        String roleName = args.length == 2 ? args[1] : null;

        Player player = playerName == null ? null : Bukkit.getPlayer(playerName);
        if (player == null) {
            roleName = playerName;
            player = Bukkit.getPlayer(sender.getName());
        }
        if (Arrays.asList(this.roles).contains(roleName))
        {
            switch (roleName)
            {
                case "Jafar" : {
                    new Jafar().init(player.getName());
                }break;
                case "Sidney" : {
                    new Sidney().init(player.getName());
                }
                case "Empereur" : {
                    new Empereur_SDF().init(player.getName());
                }break;
            }
            return true;
        } else {
            sender.sendMessage(String.format("Role incorrect, liste des roles: %s", String.join(", ", this.roles)));
            return true;
        }
    }
}
