package hundeklemmen.SkriptAddon;

import ch.njol.skript.Skript;

import ch.njol.skript.SkriptAddon;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import hundeklemmen.SkriptAddon.skript.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import ch.njol.skript.util.Getter;

import static ch.njol.skript.registrations.EventValues.registerEventValue;


public class hypermc extends JavaPlugin implements Listener {


    private static DecimalFormat format = new DecimalFormat("##.##");

    public static hypermc instance;
    public static SkriptAddon addon;
    public static PluginChannelListener pcl;

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();


        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "Return", pcl = new PluginChannelListener());

        addon = Skript.registerAddon(this);

        Skript.registerEffect(sendBungeecordMessageEffect.class, "hypermc request %number% credits from %player% with [key] %string%");
        Skript.registerEffect(SendCreditsToUserEffect.class, "hypermc send %number% credits to %player%");

        Skript.registerEffect(SendPlayerToServer.class, "hypermc send %player% to [server] %string%");

        Skript.registerEvent("hypermc purchase", SimpleEvent.class, new Class[]{bungeecordMessageReceiveEvent.class}, "hypermc purchase");
       //Skript.registerEvent("Leash Entity", SimpleEvent.class, PlayerLeashEntityEvent.class, new String[]{"[player ]leash"});
        registerEventValue(bungeecordMessageReceiveEvent.class, Player.class, new Getter<Player, bungeecordMessageReceiveEvent>() {
            @Override
            public Player get(bungeecordMessageReceiveEvent event) {
                return event.getPlayer();
            }
        }, 0);
        registerEventValue(bungeecordMessageReceiveEvent.class, String.class, new Getter<String, bungeecordMessageReceiveEvent>() {
            @Override
            public String get(bungeecordMessageReceiveEvent event) {
                return event.getKey();
            }
        }, 0);
        registerEventValue(bungeecordMessageReceiveEvent.class, Integer.class, new Getter<Integer, bungeecordMessageReceiveEvent>() {
            @Override
            public Integer get(bungeecordMessageReceiveEvent event) {
                return event.getAmount();
            }
        }, 0);


        Skript.registerEvent("hypermc vote", SimpleEvent.class, new Class[]{voteEvent.class}, "hypermc vote");
        registerEventValue(voteEvent.class, Player.class, new Getter<Player, voteEvent>() {
            @Override
            public Player get(voteEvent event) {
                return event.getPlayer();
            }
        }, 0);
        Skript.registerExpression(getUserData.class, String.class, ExpressionType.COMBINED, "hypermc [the] %string%['s] data");

        Skript.registerExpression(ExprGetJSONString.class, String.class, ExpressionType.SIMPLE, "hypermc get %string% from %string%");
        System.out.println("HyperSK enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("HyperSK disabled");
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public static void registerCommands() {

    }





}
