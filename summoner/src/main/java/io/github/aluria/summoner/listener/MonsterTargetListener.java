package io.github.aluria.summoner.listener;

import io.github.aluria.summoner.SummonerPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class MonsterTargetListener implements Listener {

  private final SummonerPlugin plugin;

  public MonsterTargetListener(SummonerPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onEntityTarget(EntityTargetLivingEntityEvent event) {
    Entity entity = event.getEntity();
    if(!(entity instanceof LivingEntity)){
      return;
    }

    // target logic.
  }
}
