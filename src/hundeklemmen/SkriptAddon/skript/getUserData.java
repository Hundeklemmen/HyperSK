package hundeklemmen.SkriptAddon.skript;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import hundeklemmen.SkriptAddon.utils;
import org.bukkit.event.Event;
import ch.njol.util.Kleenean;

import javax.annotation.Nullable;

public class getUserData extends SimpleExpression<String> {

    private Expression<String> player;
   // private Expression<String> dataType;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        player = (Expression<String>) exprs[0];
       // dataType = (Expression<String>) exprs[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return null;
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        String p = player.getSingle(event);
       // String type = dataType.getSingle(event);
       // if(type.equalsIgnoreCase("_id")||type.equalsIgnoreCase("uuid")||type.equalsIgnoreCase("uuid")||type.equalsIgnoreCase("friends")||type.equalsIgnoreCase("servers")||type.equalsIgnoreCase("username")||type.equalsIgnoreCase("credits")) {
           // String apiResult = utils.fireGet("https://hypermc.dk/api/player/" + p.getName() + "/" + type);
            //return new String[]{apiResult.toString()};
            String oofData = utils.fireGet("https://hypermc.dk/api/player/" + p + "/").toString();
            if(!oofData.equalsIgnoreCase("error")) {
                return new String[]{oofData};
            }
            return null;
        //} else {
        //    return null;
      //  }
    }
}
