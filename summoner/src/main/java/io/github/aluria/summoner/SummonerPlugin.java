package io.github.aluria.summoner;

import io.github.aluria.engine.plugin.BasePlugin;

public class SummonerPlugin extends BasePlugin {

  @Override
  public void onEnable() {
    super.onEnable();
  }

  @Override
  public void onDisable() {
    super.onDisable();
  }

  public static SummonerPlugin getInstance() {
    return getPlugin(SummonerPlugin.class);
  }
}
