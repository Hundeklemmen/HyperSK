package hundeklemmen.SkriptAddon;

import hundeklemmen.SkriptAddon.skript.bungeecordMessageReceiveEvent;
import hundeklemmen.SkriptAddon.skript.voteEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;

public class PluginChannelListener implements PluginMessageListener {

    @Override
    public synchronized void onPluginMessageReceived(String channel, Player player, byte[] message) {

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
        try {
            String subchannel = in.readUTF(); //Skal være hypermc
            String event = in.readUTF();
            Player EventPlayer = hypermc.instance.getServer().getPlayer(in.readUTF());
            if(subchannel.equalsIgnoreCase("hypermc")) {
                if (event.equalsIgnoreCase("requestcredits")) {
                    Integer amount = in.readInt();
                    String key = in.readUTF();
                    bungeecordMessageReceiveEvent Skriptevent = new bungeecordMessageReceiveEvent(EventPlayer, key, amount);
                    Bukkit.getServer().getPluginManager().callEvent(Skriptevent);
                    System.out.println(channel+" -> "+subchannel+" -> "+event+" -> "+EventPlayer.getName()+" -> "+amount+" -> "+key);
                } else if (event.equalsIgnoreCase("vote")) {
                    voteEvent Skriptevent = new voteEvent(EventPlayer);
                    Bukkit.getServer().getPluginManager().callEvent(Skriptevent);
                    System.out.println(channel+" -> "+subchannel+" -> "+event+" -> "+EventPlayer.getName());
                }
            }
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToBungeeCord(Player p, String channel, String sub, String subsub){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF(channel);
            out.writeUTF(sub);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.sendPluginMessage(hypermc.instance, "BungeeCord", b.toByteArray());
    }

}
