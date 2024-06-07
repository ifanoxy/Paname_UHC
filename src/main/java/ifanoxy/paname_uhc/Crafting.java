package ifanoxy.paname_uhc;

import ifanoxy.paname_uhc.Game.GameMain;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class Crafting implements Listener {
    private final GameMain game;

    public Crafting(GameMain gameMain) {
        this.game = gameMain;
    }
    @EventHandler
    public void Craft(CraftItemEvent event) {
        Player player = (Player) event.getWhoClicked();
        String role = this.game.playersRoles.get(player.getName());
        if (role == "Samya")
        {
            Recipe item = event.getRecipe();

            if (item.getResult().equals(new ItemStack(Material.GOLDEN_APPLE)))
            {
                player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
            }
        }
    }
}
