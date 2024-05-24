package ifanoxy.paname_uch;

import ifanoxy.paname_uch.Commands.StartCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Paname_UHC extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[Paname UHC] Lancement des commandes");
        this.loadCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadCommands() {
        this.getCommand("start").setExecutor(new StartCommand());
    }
}


