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

public class SendCreditsToUserEffect extends Effect {

    private Expression<Number> amount;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int arg1, Kleenean arg2, ParseResult arg3) {
        amount = (Expression<Number>) expressions[0];
        player = (Expression<Player>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "send " + amount.toString(arg0, arg1) + " to " + player.toString(arg0, arg1);
    }

    @Override
    protected void execute(Event event) {
        Number a = amount.getSingle(event);
        Player p = player.getSingle(event);

        if (a == null || p == null) {
            return;
        }

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("hypermc");
            out.writeUTF("SendCredits");
            out.writeInt(Math.round((long) a));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.sendPluginMessage(hypermc.instance, "BungeeCord", b.toByteArray());
    }
}