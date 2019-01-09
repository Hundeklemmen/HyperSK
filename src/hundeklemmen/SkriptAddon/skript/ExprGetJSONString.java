package hundeklemmen.SkriptAddon.skript;

import java.util.ArrayList;
import java.util.Map;


import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import com.google.gson.*;
import org.bukkit.event.Event;

public class ExprGetJSONString extends SimpleExpression<String>{

    private Expression<String> input;
    private Expression<String> string;

    public Class<? extends String> getReturnType() {

        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        input = (Expression<String>) args[0];
        string = (Expression<String>) args[1];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "get json string";
    }

    @javax.annotation.Nullable
    protected String[] get(Event arg0) {

        String in = input.getSingle(arg0);
        String s = string.getSingle(arg0);
        if(in.equalsIgnoreCase("servers")||in.equalsIgnoreCase("friends")){

            try {

                JsonParser jsonParser = new JsonParser();
                JsonObject jo = (JsonObject)jsonParser.parse(s);
                JsonArray jsonArr = jo.getAsJsonArray("servers");
                //jsonArr.
                Gson googleJson = new Gson();
                ArrayList jsonObjList = googleJson.fromJson(jsonArr, ArrayList.class);
                String[] array = (String[]) jsonObjList.toArray(new String[0]);
                return array;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        } else {
            Map<String, Object> javaMap = new Gson().fromJson(s, Map.class);
            return new String[]{javaMap.get(in).toString()};
        }
    }

}