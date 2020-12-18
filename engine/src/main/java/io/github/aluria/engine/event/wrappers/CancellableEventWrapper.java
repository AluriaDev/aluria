package io.github.aluria.engine.event.wrappers;

import org.bukkit.event.Cancellable;

public class CancellableEventWrapper extends EventWrapper implements Cancellable {

  private boolean cancelled;

  @Override
  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }

  @Override
  public boolean isCancelled() {
    return cancelled;
  }
}
