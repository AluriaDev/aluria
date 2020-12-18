package io.github.aluria.kingdoms.registries.kingdom;

import io.github.aluria.kingdoms.models.kingdom.KingdomMember;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KingdomMemberRegistry {

    private final Map<String, KingdomMember> memberMap;

    public KingdomMemberRegistry() {
        this.memberMap = new ConcurrentHashMap<>();
    }

    public void put(KingdomMember member) {
        memberMap.put(member.getPlayerName(), member);
    }

    public KingdomMember getByName(String playerName) {
        return memberMap.get(playerName);
    }

    public KingdomMember delete(String playerName) {
        return memberMap.remove(playerName);
    }

    public Collection<KingdomMember> getAll() {
        return memberMap.values();
    }
}
