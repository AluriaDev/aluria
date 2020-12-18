package io.github.aluria.kingdoms;

import io.github.aluria.engine.plugin.BasePlugin;
import io.github.aluria.kingdoms.commands.kingdom.KingdomCommand;
import io.github.aluria.kingdoms.registries.kingdom.KingdomRegistry;
import lombok.Getter;

public class KingdomsPlugin extends BasePlugin {

    @Getter
    private KingdomRegistry kingdomRegistry;

    @Override
    public void onLoad() {
        this.kingdomRegistry = new KingdomRegistry();
    }

    @Override
    public void onEnable() {
        this.registerCommands(
          new KingdomCommand(this)
        );
    }

    @Override
    public void onDisable() {

    }

    public static KingdomsPlugin getInstance() {
        return getPlugin(KingdomsPlugin.class);
    }
}
