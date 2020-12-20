package io.github.aluria.kingdoms.registries.kingdom;

import io.github.aluria.kingdoms.models.kingdom.KingdomMember;
import io.github.aluria.kingdoms.models.kingdom.KingdomUser;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KingdomUserRegistry {

    private final Map<String, KingdomUser> userMap;

    public KingdomUserRegistry() {
        this.userMap = new ConcurrentHashMap<>();
    }

    public void put(KingdomUser user) {
        userMap.put(user.getPlayerName(), user);
    }

    public KingdomUser getByName(String playerName) {
        return userMap.get(playerName);
    }

    public void delete(String playerName) {
        userMap.remove(playerName);
    }

    public Collection<KingdomUser> getAll() {
        return userMap.values();
    }
}
