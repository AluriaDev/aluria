package io.github.aluria.kingdoms.commands.kingdom;

import io.github.aluria.kingdoms.KingdomsPlugin;
import io.github.aluria.kingdoms.enums.role.RolePermission;
import io.github.aluria.kingdoms.models.invite.Invite;
import io.github.aluria.kingdoms.models.kingdom.Kingdom;
import io.github.aluria.kingdoms.models.kingdom.KingdomMember;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class InviteCommand {

    private final KingdomsPlugin plugin;

    public InviteCommand(KingdomsPlugin plugin) {
        this.plugin = plugin;
    }

    public void onCommand(Player player, String label, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cUtilize: /" + label + " convidar <jogador>.");
            return;
        }

        final KingdomMember member = plugin.getMemberRegistry().getByName(player.getName());
        if(member == null) return;

        if(!member.hasKingdom()) {
            player.sendMessage("§cVocê não faz parte de um reino. Crie um utilizando o comando /reino criar.");
            return;
        }

        final Kingdom kingdom = plugin.getKingdomRegistry().getById(member.getKingdomId());

        if(!kingdom.canInvite()) {
            player.sendMessage("§cO reino no qual você faz parte já atingiu o máximo de membros.");
            return;
        }

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("§cO jogador citado não está online ou não existe.");
            return;
        }

        if(target.equals(player)) {
            player.sendMessage("§cVocê não pode convidar à si mesmo.");
            return;
        }

        if(kingdom.getMember(target) != null) {
            player.sendMessage("§cO jogador citado já faz parte do reino no qual você pertence.");
            return;
        }

        if(plugin.getKingdomRegistry().getByMember(target.getName()) != null) {
            player.sendMessage("§cO jogador citado já faz parte de um reino.");
            return;
        }

        if (plugin.getInviteRegistry().getByInvitedName(target.getName(), kingdom.getId()) != null) {
            player.sendMessage("§cJá existe um convite pendente para este jogador.");
            return;
        }

        final Invite invite = new Invite(
          UUID.randomUUID(),
          kingdom.getId(),
          target.getName(),
          player.getName()
        );

        plugin.getInviteRegistry().put(invite);

        player.sendMessage("§aConvite para reino enviado com sucesso para o jogador " + target.getName() + ".");
        target.sendMessage("§aVocê recebeu um convite para entrar no reino [" + kingdom.getTag() + "] " + kingdom.getName() + ". " +
          "Para aceitar utilize /reino entrar " + kingdom.getTag() + ".");
    }
}
