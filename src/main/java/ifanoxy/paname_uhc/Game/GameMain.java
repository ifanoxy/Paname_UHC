package ifanoxy.paname_uhc.Game;

import ifanoxy.paname_uhc.Paname_UHC;
import ifanoxy.paname_uhc.Roles.*;
import ifanoxy.paname_uhc.TimerJeu;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;

public class GameMain {

    public TimerJeu timer;
    public List<Player> playersList;
    public ScoreboardGame scoreboard;
    public CommandSender sender;
    public Server server;
    public HashMap<String, String> playersRoles;
    public WorldBorder wb;
    public Paname_UHC plugin;
    public HashMap<String, Integer> kills;
    public HashMap<String, String> playersTeam;
    public Boolean ramadan = false;
    public HashMap<String, String> playersPoulets;

    private GameMain myInstance(){
        return this;
    }

    public void init(List<Player> players, Paname_UHC plugin) throws InterruptedException {

        this.server = Bukkit.getServer();
        this.sender = this.server.getConsoleSender();
        this.plugin = plugin;
        this.playersList = players;

        server.broadcastMessage("Création du monde en cours...");

        server.dispatchCommand(this.sender, "mv delete UHC_GAME");
        server.dispatchCommand(this.sender, "mvconfirm");

        server.dispatchCommand(this.sender, "mv create UHC_GAME normal");
        server.dispatchCommand(this.sender, "mv gamerule naturalRegeneration false UHC_game");

        server.broadcastMessage("Création du monde terminé !");
        server.broadcastMessage("Pré-génération du monde en cours...");


        final int allChunks = 18900;
        final int chunkIncrement = 16;
        final int delayBetweenChunks = 1; // (1 tick = 50ms)

        new BukkitRunnable() {
            int chunkSize = 0;
            int x = -1000;
            int z = -1000;

            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    if (x > 1000) {
                        x = -1000;
                        z += chunkIncrement;
                    }

                    if (z > 1000) {
                        afterPreGen(players);
                        cancel();
                        return;
                    }

                    Chunk c = Bukkit.getWorld("UHC_GAME").getChunkAt(x >> 4, z >> 4);
                    c.load();
                    c.unload();
                    chunkSize += 1;

                    if (chunkSize % 100 == 0) {
                        Bukkit.broadcastMessage("Gen : " + ChatColor.GREEN + (allChunks - chunkSize) + "§f/" + allChunks + "(" + ChatColor.GREEN + (100 - Math.round(chunkSize * 100 / allChunks)) + "§c%§f)");
                    }
                }

                x += 50 * chunkIncrement;
            }
        }.runTaskTimer(this.plugin, 0L, delayBetweenChunks);


    }

    private void afterPreGen(List<Player> players) {
        server.broadcastMessage("Pré-génération du monde terminé !");

        Bukkit.getWorld("UHC_GAME").setPVP(false);

        kills = new HashMap<>();
        this.playersPoulets = new HashMap<String, String>();
        this.playersTeam = new HashMap<String, String>();
        this.playersRoles = new HashMap<String, String>();

        plugin.eventGame(myInstance());

        for (Player p : players)
        {
            p.sendMessage("Téléportation en cours...");
            randomTeleport(p);
            kills.put(p.getName(), 0);
        }


        System.out.println("Lancement du timer");

        timer = new TimerJeu();
        timer.startTimer(myInstance());
        wb = new WorldBorder();

        wb.init();


        scoreboard = new ScoreboardGame();
        scoreboard.init(myInstance());
    }

    public <K, V> int countKeysWithValue(Map<K, V> map, V value) {
        int count = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                count++;
            }
        }
        return count;
    }

    public void distributionsDesRoles() {
        String[] teams = new String[]{"Sidney", "Famille de Jafar", "Français", "SDF"};
        HashMap<String, String[]> roles = new HashMap<String, String[]>();
        roles.put("Sidney", new String[]{"Sidney"});
        roles.put("Famille de Jafar", new String[]{"Samya", "Nacima", "Jafar"});
        roles.put("Français", new String[]{"Alwin", "Leo", "Mady", "Rafael", "Clotaire"});
        roles.put("SDF", new String[]{"Empereur des SDF", "SDF voleur", "SDF sale", "SDF croyant", "SDF obèse", "SDF esquive"});


        for (final Player player : this.playersList) {
            int rdm1 = new Random().nextInt(teams.length);
            String teamName = teams[rdm1];

            while (this.playersTeam.size() == 1 && this.playersTeam.containsValue(teamName))
            {
                rdm1 = new Random().nextInt(teams.length);
                teamName = teams[rdm1];
            }

            if (teamName == "Sidney")
            {
                teams = removeElement(teams, rdm1);
            } else if (teamName == "Famille de Jafar") {
                if (this.countKeysWithValue(this.playersTeam, "Famille de Jafar") == 2)
                {
                    teams = removeElement(teams, rdm1);
                }
            } else if (teamName == "Français") {
                if (this.countKeysWithValue(this.playersTeam, "Français") == 4)
                {
                    teams = removeElement(teams, rdm1);
                }
            } else {
                if (this.countKeysWithValue(this.playersTeam, "SDF") == 5)
                {
                    teams = removeElement(teams, rdm1);
                }
            }

            player.sendMessage(
                    String.format("§aVous êtes dans la famille §b§l%s§a !", teamName)
            );

            playersTeam.put(player.getName(), teamName);


            int rdm2 = new Random().nextInt(roles.get(teamName).length);
            String roleName = roles.get(teamName)[rdm2];
            roles.put(teamName, removeElement(roles.get(teamName), rdm2));

            player.sendMessage(
                    String.format("§aVous jouez le role §d§l%s§a !", roleName)
            );

            setRole(player, roleName);

            this.playersRoles.put(player.getName(), roleName);
        }
    }

    public void setRole(Player player, String roleName) {
        String groupName = null;
        switch (roleName)
        {
            case "Jafar" : {
                new Jafar().init(player.getName());
                groupName = "jf_jafar";
            }break;
            case "Samya" : {
                groupName = "jf_samya";
            }break;
            case "Nacima" : {
                new Nacima().init(player.getName(), this);
                groupName = "jf_nacima";
            }break;
            case "Sidney" : {
                new Sidney().init(player.getName(), this.plugin);
                groupName = "sidney";
            }break;
            case "Clotaire" : {
                new Clotaire().init(player.getName());
                groupName = "francais_clotaire";
            }break;
            case "Leo" : {
                new Leo().init(player.getName(), this.plugin);
                groupName = "francais_leo";
            }break;
            case "Alwin" : {
                new Alwin().init(player.getName(), this.plugin);
                groupName = "francais_alwin";
            }break;
            case "Mady" : {
                groupName = "francais_mady";
            }break;
            case "Rafael" : {
                new Rafael().init(player.getName());
                groupName = "francais_rafael";
            }break;
            case "Empereur des SDF" : {
                groupName = "sdf_empereur";
                new Empereur_SDF().init(player.getName(), this);
            }break;
            case "SDF voleur" : {
                new Voleur_SDF().init(player.getName(), this.plugin);
                groupName = "sdf_voleur";
            }break;
            case "SDF sale" : {
                new Sale_SDF().init(player.getName(), this.plugin);
                groupName = "sdf_sale";
            }break;
            case "SDF croyant" : {
                groupName = "sdf_croyant";
            }break;
            case "SDF obèse" : {
                groupName = "sdf_obèse";
            }break;
            case "SDF esquive" : {
                groupName = "sdf_esquive";
            }break;
        }

        if (groupName == null)return;

        this.server.dispatchCommand(
                this.sender,
                String.format(
                        "lp user %s group set %s",
                        player.getName(),
                        groupName
                        ));
    }
    public static int generateRandomNumber() {
        Random random = new Random();
        boolean positiveRange = random.nextBoolean();
        if (positiveRange) {
            return 250 + random.nextInt(750);
        } else {
            return -1000 + random.nextInt(750);
        }
    }

    public void randomTeleport(Player player)
    {
        World world = Bukkit.getWorld("UHC_GAME");
        int x = generateRandomNumber();
        int z = generateRandomNumber();
        int y = Objects.requireNonNull(world).getHighestBlockYAt(x, z) < 60 ? 100 : Objects.requireNonNull(world).getHighestBlockYAt(x, z);

        Location loc = new Location(world, x, y, z);

        int i = 0;

        while (world.getBlockAt(loc).getType().equals(Material.WATER) && i <= 10)
        {
            System.out.println(world.getBlockAt(new Location(world, x, y - 1, z)));
            System.out.println("Dans la flotte azoidju aiozi dioazidoip azidoipaiz dopaizpodioazpd");
            x = generateRandomNumber();
            z = generateRandomNumber();
            y = Objects.requireNonNull(world).getHighestBlockYAt(x, z);
            i += 1;
        }
        player.teleport(loc);
    }
    public static String[] removeElement(String[] originalArray, int index) {
        if (originalArray == null || index < 0 || index >= originalArray.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        String[] newArray = new String[originalArray.length - 1];

        for (int i = 0, k = 0; i < originalArray.length; i++) {
            if (i == index) {
                continue;
            }
            newArray[k++] = originalArray[i];
        }

        return newArray;
    }
}
