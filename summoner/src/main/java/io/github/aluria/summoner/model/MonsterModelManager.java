package io.github.aluria.summoner.model;

import com.google.common.collect.Maps;

import java.util.Map;

public final class MonsterModelManager {

  private final Map<Integer, MonsterModel> monsterModelMap = Maps.newConcurrentMap();

  public MonsterModel getModelById(int id) {
    return monsterModelMap.get(id);
  }

  public MonsterModel getModelByDisplayName(String displayName) {
    return monsterModelMap.values()
      .parallelStream()
      .filter(model -> model.getDisplayName().equalsIgnoreCase(displayName))
      .findAny()
      .orElse(null);
  }

  public Map<Integer, MonsterModel> getMonsterModelMap() {
    return monsterModelMap;
  }
}
