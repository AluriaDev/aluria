package io.github.aluria.kingdoms;

import io.github.aluria.engine.plugin.BasePlugin;
import io.github.aluria.kingdoms.commands.kingdom.KingdomCommand;
import io.github.aluria.kingdoms.listeners.ConnectionListener;
import io.github.aluria.kingdoms.registries.invite.InviteRegistry;
import io.github.aluria.kingdoms.registries.kingdom.KingdomMemberRegistry;
import io.github.aluria.kingdoms.registries.kingdom.KingdomRegistry;
import lombok.Getter;

@Getter
public class KingdomsPlugin extends BasePlugin {

    private KingdomRegistry kingdomRegistry;
    private KingdomMemberRegistry memberRegistry;
    private InviteRegistry inviteRegistry;

    @Override
    public void onLoad() {
        this.kingdomRegistry = new KingdomRegistry();
        this.memberRegistry = new KingdomMemberRegistry();
        this.inviteRegistry = new InviteRegistry();
    }

    @Override
    public void onEnable() {
        this.registerCommands(
          new KingdomCommand(this)
        );

        this.registerListeners(
          new ConnectionListener(this)
        );
    }

    @Override
    public void onDisable() {

    }

    public static KingdomsPlugin getInstance() {
        return getPlugin(KingdomsPlugin.class);
    }
}
