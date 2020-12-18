package io.github.aluria.engine.event.wrappers;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EventWrapper extends Event {

  private static final HandlerList handlerList = new HandlerList();

  public static HandlerList getHandlerList() {
    return handlerList;
  }

  @Override
  public HandlerList getHandlers() {
    return handlerList;
  }
}
