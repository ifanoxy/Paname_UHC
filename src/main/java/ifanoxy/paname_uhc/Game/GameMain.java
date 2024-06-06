package ifanoxy.paname_uhc.Game;

import ifanoxy.paname_uhc.Paname_UHC;
import ifanoxy.paname_uhc.Roles.Clotaire;
import ifanoxy.paname_uhc.Roles.Empereur_SDF;
import ifanoxy.paname_uhc.Roles.Jafar;
import ifanoxy.paname_uhc.Roles.Sidney;
import ifanoxy.paname_uhc.TimerJeu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

    public void init(List<Player> players, Paname_UHC plugin) {

        this.server = Bukkit.getServer();
        this.sender = this.server.getConsoleSender();
        this.plugin = plugin;

        server.broadcastMessage("Création du monde en cours...");

        server.dispatchCommand(this.sender, "mv delete UHC_GAME");
        server.dispatchCommand(this.sender, "mvconfirm");
        server.dispatchCommand(this.sender, "mv create UHC_GAME normal");

        server.broadcastMessage("Création du monde terminé !");

        this.kills = new HashMap<>();

        for (Player p : players)
        {
            p.sendMessage("Téléportation en cours...");
            this.randomTeleport(p);
            this.kills.put(p.getName(), 0);
        }

        this.plugin.eventGame(this);

        this.playersList = players;
        this.distributionsDesRoles();

        System.out.println("Lancement du timer");

        this.timer = new TimerJeu();
        this.timer.startTimer();
        this.wb = new WorldBorder();

        this.wb.init();


        this.scoreboard = new ScoreboardGame();
        this.scoreboard.init(this);

    }

    public void distributionsDesRoles() {
        String[] teams = new String[]{"Sidney", "Famille de Jafar", "Français", "SDF"};
        HashMap<String, String[]> roles = new HashMap<String, String[]>();
        roles.put("Sidney", new String[]{"Sidney"});
        roles.put("Famille de Jafar", new String[]{"Samya", "Nacima", "Jafar"});
        roles.put("Français", new String[]{"Alwin", "Leo", "Mady", "Rafael", "Clotaire"});
        roles.put("SDF", new String[]{"Empereur des SDF", "SDF voleur", "SDF sale", "SDF croyant", "SDF obèse", "SDF esquive"});

        HashMap<String, String> playersTeam = new HashMap<String, String>();
        this.playersRoles = new HashMap<String, String>();

        for (final Player player : this.playersList) {
            int rdm1 = new Random().nextInt(teams.length);
            String teamName = teams[rdm1];

            while (playersTeam.containsValue(teamName) && playersTeam.size() <= 2)
            {
                teamName = teams[rdm1];
            }

            player.sendMessage(
                    String.format("§aVous êtes dans la famille §b§l%s§a !", teamName)
            );

            playersTeam.put(player.getName(), teamName);


            int rdm2 = new Random().nextInt(roles.get(teamName).length);
            String roleName = roles.get(teamName)[rdm2];
            removeTheElement(roles.get(teamName), rdm2);

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
                groupName = "jf_nacima";
            }break;
            case "Sidney" : {
                new Sidney().init(player.getName());
                groupName = "sidney";
            }break;
            case "Clotaire" : {
                new Clotaire().init(player.getName());
                groupName = "francais_clotaire";
            }break;
            case "Leo" : {
                groupName = "francais_leo";
            }break;
            case "Alwin" : {
                groupName = "francais_alwin";
            }break;
            case "Mady" : {
                groupName = "francais_mady";
            }break;
            case "Rafael" : {
                groupName = "francais_rafael";
            }break;
            case "Empereur des SDF" : {
                groupName = "sdf_empereur";
                new Empereur_SDF().init(player.getName());
            }break;
            case "SDF voleur" : {
                groupName = "sdf_voleur";
            }break;
            case "SDF sale" : {
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
                        "lp user %s group add %s",
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
        int x = generateRandomNumber();
        int z = generateRandomNumber();
        int y = Objects.requireNonNull(Bukkit.getWorld("UHC_GAME")).getHighestBlockYAt(x, z);
        player.teleport(new Location(Bukkit.getWorld("UHC_GAME"), x, y, z));
    }
    public static void removeTheElement(String[] arr, int index)
    {
        if (arr == null || index < 0
                || index >= arr.length) {

            return;
        }

        String[] anotherArray = new String[arr.length - 1];

        for (int i = 0, k = 0; i < arr.length; i++) {

            if (i == index) {
                continue;
            }

            anotherArray[k++] = arr[i];
        }

    }
}
