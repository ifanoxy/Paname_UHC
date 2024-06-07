package ifanoxy.paname_uhc.Roles;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Rafael {
    public void init(String pseudo) {
        Player player = Bukkit.getPlayer(pseudo);
        ItemStack chemise = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = chemise.getItemMeta();
        meta.setDisplayName("Chemise bien classe");
        meta.setLore(Arrays.asList("§7Avec la puissance de cette chemise", "§7Vous résisterez au coups et en plus", "§7le tissu de votre chemise est incassable."));
        chemise.setItemMeta(meta);
        chemise.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        player.getInventory().setChestplate(chemise);
    }
}
