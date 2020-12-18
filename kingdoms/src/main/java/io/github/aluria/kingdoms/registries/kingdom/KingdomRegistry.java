package io.github.aluria.kingdoms.registries.kingdom;

import com.google.common.collect.Maps;
import io.github.aluria.kingdoms.models.kingdom.Kingdom;
import org.bukkit.Location;

import java.util.*;

public class KingdomRegistry {

    private final Map<UUID, Kingdom> kingdomMap;

    public KingdomRegistry() {
        this.kingdomMap = Maps.newConcurrentMap();
    }

    public void put(Kingdom kingdom) {
        kingdomMap.put(kingdom.getId(), kingdom);
    }

    public void remove(UUID id) {
        kingdomMap.remove(id);
    }

    public Kingdom getById(UUID id) {
        return kingdomMap.get(id);
    }

    public Kingdom getByName(String name) {
        for (Kingdom kingdom : getAll()) {
            if (kingdom.getName().equals(name)) {
                return kingdom;
            }
        }

        return null;
    }

    public Kingdom getByTag(String tag) {
        for (Kingdom kingdom : getAll()) {
            if(kingdom.getTag().equals(tag)) {
                return kingdom;
            }
        }

        return null;
    }

    public Kingdom getByMember(String name) {
        for (Kingdom kingdom : getAll()) {
            if(kingdom.getMembers().containsKey(name)) {
                return kingdom;
            }
        }

        return null;
    }

    public Set<Kingdom> getInRange(Location location, int range) {
        Set<Kingdom> copy = new HashSet<>();

        for (Kingdom kingdom : getAll()) {
            if(!isInRange(kingdom.getCenterLocation(), location, range)) continue;

            copy.add(kingdom);
        }

        return copy;
    }

    private boolean isInRange(Location location, Location otherLocation, int range) {
        try {
            return location.distance(otherLocation) <= range;
        } catch (Exception e) {
            return false;
        }
    }

    public Collection<Kingdom> getAll() {
        return kingdomMap.values();
    }
}
