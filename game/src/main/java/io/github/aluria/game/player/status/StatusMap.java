package io.github.aluria.game.player.status;

import com.google.common.collect.Maps;

import java.util.Map;

public final class StatusMap {

  private final Map<StatusType, Integer> statusMap = Maps.newConcurrentMap();

  public int getStatusValue(StatusType type) {
    return statusMap.getOrDefault(type, 0);
  }

  public void setStatusValue(StatusType type, int value) {
    statusMap.put(type, value);
  }

  public void incrementStatusValue(StatusType type, int value) {
    this.setStatusValue(type, getStatusValue(type) + value);
  }

  public Map<StatusType, Integer> getStatusMap() {
    return statusMap;
  }
}
