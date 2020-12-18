package io.github.aluria.summoner.model.area;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

@Data
@AllArgsConstructor
public final class SpawnRange {

  private World world;
  private Vector minimumPosition;
  private Vector maximumPosition;


  public boolean isInRange(Location location) {
    return isInRange(location.getBlockX(), location.getBlockY(), location.getBlockZ());
  }

  public boolean isInRange(int x, int y, int z) {
    double minimumPositionX = minimumPosition.getX();
    double minimumPositionY = minimumPosition.getY();
    double minimumPositionZ = minimumPosition.getZ();

    double maximumPositionX = maximumPosition.getX();
    double maximumPositionY = maximumPosition.getY();
    double maximumPositionZ = maximumPosition.getZ();


    return (x >= minimumPositionX) && (x <= maximumPositionX)
      && (y >= minimumPositionY && y <= maximumPositionY)
      && (z >= minimumPositionZ && z <= maximumPositionZ);
  }
}
