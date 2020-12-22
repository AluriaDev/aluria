package io.github.aluria.game.player.event.database;

import io.github.aluria.game.player.Gamer;
import io.github.aluria.game.player.event.GamerEvent;

public final class GamerUpdateEvent extends GamerEvent {

  public GamerUpdateEvent(Gamer gamer) {
    super(gamer);
  }

}
