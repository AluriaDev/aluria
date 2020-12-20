package io.github.aluria.kingdoms.tasks.user;

import io.github.aluria.kingdoms.KingdomsPlugin;
import io.github.aluria.kingdoms.models.kingdom.Kingdom;
import io.github.aluria.kingdoms.models.kingdom.KingdomMember;
import io.github.aluria.kingdoms.models.kingdom.KingdomUser;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class UserReputationTask implements Runnable {

    private static final long ONE_HOUR_IN_MILLIS = TimeUnit.HOURS.toMillis(1);

    private final KingdomsPlugin plugin;

    @Override
    public void run() {
        final long now = System.currentTimeMillis();

        for (KingdomUser user : plugin.getUserRegistry().getAll()) {
            if(user.getReputation() >= 5) continue;

            process(user, now);
        }
    }

    private void process(KingdomUser user, long now) {
        final long joinDate = user.getJoinDate();
        final long diff = now - joinDate;

       // TODO
    }
}
