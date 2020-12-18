package io.github.aluria.game.entity.player.status;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class GamerStatusMap {

  private final Map<GamerStatusType, Integer> statusMap = new ConcurrentHashMap<>();

  public int getStatusValue(GamerStatusType type) {
    return statusMap.getOrDefault(type, 0);
  }

  public void setStatusValue(GamerStatusType type, int value) {
    statusMap.put(type, value);
  }

  public void incrementStatusValue(GamerStatusType type, int value) {
    this.setStatusValue(type, getStatusValue(type) + value);
  }

  public Map<GamerStatusType, Integer> getStatusMap() {
    return statusMap;
  }
}
