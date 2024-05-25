package ifanoxy.paname_uhc;

import ifanoxy.paname_uhc.Commands.StartCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Paname_UHC extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("[Paname UHC] Lancement des commandes");
        this.loadCommands();
        this.loadEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadCommands() {
        this.getCommand("start").setExecutor(new StartCommand());
    }
    private void loadEvents() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void on(ServerListPingEvent event) {
        event.setMotd(
                "§e§l Paname UHC - §6§lServeur UHC 1.8.9 §c§lInédits\n       §7§oEn cours de développement"
        );
    }
}


