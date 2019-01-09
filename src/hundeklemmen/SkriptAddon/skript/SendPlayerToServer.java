package hundeklemmen.SkriptAddon.skript;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import hundeklemmen.SkriptAddon.hypermc;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SendPlayerToServer extends Effect {

    private Expression<Player> player;
    private Expression<String> server;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) expressions[0];
        server = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event event) {
        if (player == null)  return;
        if (server == null)  return;
        Player p = player.getSingle(event);
        String s = server.getSingle(event);

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.sendPluginMessage(hypermc.instance, "BungeeCord", b.toByteArray());
    }
}