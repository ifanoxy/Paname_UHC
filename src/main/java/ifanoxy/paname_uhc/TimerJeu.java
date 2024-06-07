package ifanoxy.paname_uhc;

import java.util.Timer;
import java.util.TimerTask;

public class TimerJeu {
    private static int seconds = 0;
    private static WorldBorder worldBorder;
    private GameMain game;

    public static void setWorldBorder(WorldBorder worldBorder) {
        TimerJeu.worldBorder = worldBorder;
    }

    public int getSeconds() {
        return seconds;
    }

    public void startTimer(GameMain gameMain) {
        this.game = gameMain;
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                game.server.broadcastMessage("");
                game.server.broadcastMessage("§cLe pvp vient d'être activé !");
                game.server.broadcastMessage("");
                Bukkit.getWorld("UHC_GAME").setPVP(true);
            }
        }, 20 * 60 * 1000);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.game.plugin, new Runnable() {
            @Override
            public void run() {
                seconds++;

                if (seconds % 10 == 0) {
                    worldBorder.executeCommand();
                }

                if (seconds == 60 * 1)
                {
                    for (Player p : game.playersList)
                    {
                        new Event_Poulet().init(p.getName(), game);
                    }
                    game.distributionsDesRoles();
                }

                if (seconds % (10 * 60) == 0 && seconds > 60 * 20)
                {
                    spawnRandomEvent();
                }
            }
        }, 20, 20);
    }

    private void spawnRandomEvent() {
        final int rdm = new Random().nextInt(10);

        switch (rdm) {
            case 1 : {
                this.game.server.broadcastMessage("");
                this.game.server.broadcastMessage("§dCe mois-ci, c'est le RAMADAN ! Les musulmans n'ont plus le droit de manger sauf à la tombé de la nuit. (pendant 5 minutes)");
                this.game.server.broadcastMessage("");
                this.game.ramadan = true;

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        game.ramadan = false;
                    }
                }, 1000 * 60 * 5);
            }break;
            case 5: {
                // Apparition de farouk
            }break;
            case 9: {
                // Pouletot distributot
            }break;
            default: return;
        }
    }


    @Override
    public String toString() {
        int seconds = this.seconds % 60;
        int minutes = (int) Math.floor(this.seconds / 60);
        int hours = (int) Math.floor(minutes / 60);
        minutes = minutes % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
