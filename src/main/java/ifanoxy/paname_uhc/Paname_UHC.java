package ifanoxy.paname_uhc;

import ifanoxy.paname_uhc.Commands.StartCommand;
import ifanoxy.paname_uhc.Game.GameMain;
import ifanoxy.paname_uhc.Others.DayCycleTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Paname_UHC extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("[Paname UHC] Lancement des commandes");
        new DayCycleTask().runTaskTimer(this, 0L, 1L);
        this.loadCommands();
        this.loadEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadCommands() {
        this.getCommand("start").setExecutor(new StartCommand(this));
    }
    private void loadEvents() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void eventGame(GameMain gameMain) {
        getServer().getPluginManager().registerEvents(new PlayerDeath(gameMain), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(gameMain), this);
        getServer().getPluginManager().registerEvents(new Chat(gameMain), this);
        getServer().getPluginManager().registerEvents(new Mange(gameMain), this);
        getServer().getPluginManager().registerEvents(new Crafting(gameMain), this);
    }

    @EventHandler
    public void on(ServerListPingEvent event) {
        event.setMotd(
                "§e§l Paname UHC - §6§lServeur UHC 1.8.9 §c§lInédits\n       §7§oEn cours de développement"
        );
    }
}


