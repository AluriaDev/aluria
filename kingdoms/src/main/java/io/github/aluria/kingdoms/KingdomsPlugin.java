package io.github.aluria.kingdoms;

import io.github.aluria.engine.plugin.BasePlugin;
import io.github.aluria.kingdoms.commands.kingdom.KingdomCommand;
import io.github.aluria.kingdoms.listeners.ConnectionListener;
import io.github.aluria.kingdoms.listeners.ProtectionListener;
import io.github.aluria.kingdoms.registries.invite.InviteRegistry;
import io.github.aluria.kingdoms.registries.kingdom.KingdomUserRegistry;
import io.github.aluria.kingdoms.registries.kingdom.KingdomRegistry;
import io.github.aluria.kingdoms.tasks.user.UserReputationTask;
import lombok.Getter;

@Getter
public class KingdomsPlugin extends BasePlugin {

    private KingdomRegistry kingdomRegistry;
    private KingdomUserRegistry userRegistry;
    private InviteRegistry inviteRegistry;

    @Override
    public void onLoad() {
        this.kingdomRegistry = new KingdomRegistry();
        this.userRegistry = new KingdomUserRegistry();
        this.inviteRegistry = new InviteRegistry();
    }

    @Override
    public void onEnable() {
        this.registerCommands(
          new KingdomCommand(this)
        );

        this.registerListeners(
          new ConnectionListener(this),
          new ProtectionListener(this)
        );

        this.getServer().getScheduler().runTaskTimer(
          this,
          new UserReputationTask(this),
          0L,
          20L * 60L * 10
        );
    }

    public static KingdomsPlugin getInstance() {
        return getPlugin(KingdomsPlugin.class);
    }
}
