package ifanoxy.paname_uhc.Game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Event_Poulet {
    public void init(String pseudo, GameMain game) {
        Player player = Bukkit.getPlayer(pseudo);
        ItemStack poulet = new ItemStack(Material.COOKED_CHICKEN);
        ItemMeta meta = poulet.getItemMeta();

        int rand = new Random().nextInt(4);

        switch (rand) {
            case 0 : {
                meta.setDisplayName("Poulet cru");
                meta.setLore(Arrays.asList("Ouhlala vraiment le poulet là!"));

                Bukkit.dispatchCommand(game.sender, String.format("speed walk 1.08 %s", pseudo));
            }break;
            case 1 : {
                meta.setDisplayName("Poulet Mi-Cuit");
                meta.setLore(Arrays.asList("Le poulet c'est trop bon"));

                game.playersPoulets.put(pseudo, "Poulet Mi-Cuit");
            }break;
            case 2 : {
                meta.setDisplayName("Poulet Cuit");
                meta.setLore(Arrays.asList("Là je pleurs sur le poulet"));

                game.playersPoulets.put(pseudo, "Poulet Cuit");
            }break;
            case 3 : {
                meta.setDisplayName("Poulet Bien Cuit");
                meta.setLore(Arrays.asList("Le poulet il est seccos la"));

                game.playersPoulets.put(pseudo, "Poulet Bien Cuit");
            }break;
        }
        player.getInventory().addItem(poulet);
    }
}
