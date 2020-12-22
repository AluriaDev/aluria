package io.github.aluria.game.player.upgrade;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class UpgradeMap {

  private final Map<UpgradeType, Integer> upgradeMap = new ConcurrentHashMap<>();

  public final int getUpgradeLevel(UpgradeType upgradeType) {
    return upgradeMap.getOrDefault(upgradeType, 1);
  }

  public final boolean setUpgradeLevel(UpgradeType upgradeLevel, int level) {
    return upgradeMap.put(upgradeLevel, level) != null;
  }

  public final boolean incrementUpgradeLevel(UpgradeType type, int value) {
    return setUpgradeLevel(type, getUpgradeLevel(type) + value);
  }

  public final boolean decrementUpgradeLevel(UpgradeType type, int value) {
    return setUpgradeLevel(type, getUpgradeLevel(type) - value);
  }

  public Map<UpgradeType, Integer> getUpgradeMap() {
    return upgradeMap;
  }
}
