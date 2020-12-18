package io.github.aluria.game;

import io.github.aluria.engine.plugin.BasePlugin;

public final class GamePlugin extends BasePlugin {

  @Override
  public void onEnable() {

  }

  @Override
  public void onDisable() {

  }

  public static GamePlugin getInstance() {
    return getPlugin(GamePlugin.class);
  }
}
