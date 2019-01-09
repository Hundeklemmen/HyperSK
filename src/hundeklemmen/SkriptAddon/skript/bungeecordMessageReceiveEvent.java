package hundeklemmen.SkriptAddon.skript;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class bungeecordMessageReceiveEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private String key;
    private Integer amount;
    private Boolean cancelled = false;

    public bungeecordMessageReceiveEvent(Player player, String key, Integer amount) {
        this.player = player;
        this.key = key;
        this.amount = amount;
    }

    public Player getPlayer() {
        return player;
    }

    public String getKey() {
        return key;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
