package io.github.aluria.kingdoms.commands.kingdom;

import io.github.aluria.kingdoms.KingdomsPlugin;
import io.github.aluria.kingdoms.enums.role.Role;
import io.github.aluria.kingdoms.models.invite.Invite;
import io.github.aluria.kingdoms.models.kingdom.Kingdom;
import io.github.aluria.kingdoms.models.kingdom.KingdomMember;
import io.github.aluria.kingdoms.models.kingdom.KingdomUser;
import org.bukkit.entity.Player;

public class JoinCommand {

    private final KingdomsPlugin plugin;

    public JoinCommand(KingdomsPlugin plugin) {
        this.plugin = plugin;
    }

    public void onCommand(Player player, String label, String[] args) {
        if(args.length < 1) {
            player.sendMessage("§cUtilize: /" + label + " entrar <tag>.");
            return;
        }

        final KingdomUser user = plugin.getUserRegistry().getByName(player.getName());
        if (user.hasKingdom()) {
            player.sendMessage("§cVocê já faz parte de um reino.");
            return;
        }

        final String tag = args[0].toUpperCase();

        final Kingdom kingdom = plugin.getKingdomRegistry().getByTag(tag);
        if(kingdom == null) {
            player.sendMessage("§cO reino citado não existe.");
            return;
        }

        final Invite invite = plugin.getInviteRegistry().getByInvitedName(player.getName(), kingdom.getId());
        if(invite == null) {
            player.sendMessage("§cVocê não recebeu um convite para entrar neste reino.");
            return;
        }

        if(!kingdom.canInvite()) {
            plugin.getInviteRegistry().remove(invite.getId());
            player.sendMessage("§cEste reino já atingiu o número máximo de membros.");
            return;
        }

        player.sendMessage("§a§lYAY! §aAgora, você faz parte do reino [" + kingdom.getTag() + "] " + kingdom.getName() + "!");

        user.setRole(Role.RECRUIT);
        user.setKingdomId(kingdom.getId());

        kingdom.getBroadcaster().broadcast(
          "",
          " §e* O jogador " + player.getName() + " entrou para o reino.",
          " "
        );

        kingdom.addMember(new KingdomMember(
          player.getName(),
          Role.RECRUIT,
          user.getReputation()
        ));

        kingdom.calculateTotalReputation();
    }
}
