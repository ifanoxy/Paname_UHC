package ifanoxy.paname_uhc.Others;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class DayCycleTask extends BukkitRunnable {

    private static final long FULL_DAY_TICKS = 24000L; // 24000 ticks dans un cycle complet de 20 minutes
    private static final long CUSTOM_DAY_TICKS = 48000L; // 12000 ticks pour un cycle de 10 minutes

    private long tickCounter = 0;

    @Override
    public void run() {
        World world = Bukkit.getWorld("UHC_GAME");
        if (world == null)return;
        long currentTime = ((tickCounter * CUSTOM_DAY_TICKS) / FULL_DAY_TICKS) % FULL_DAY_TICKS;
        world.setTime(currentTime);
        tickCounter++;
        tickCounter %= 24000;
    }
}