package io.github.aluria.game.player.event;

import io.github.aluria.engine.event.wrappers.EventWrapper;
import io.github.aluria.game.player.Gamer;

public class GamerEvent extends EventWrapper {

  private final Gamer gamer;

  public GamerEvent(Gamer gamer) {
    this.gamer = gamer;
  }

  public Gamer getGamer() {
    return gamer;
  }
}
