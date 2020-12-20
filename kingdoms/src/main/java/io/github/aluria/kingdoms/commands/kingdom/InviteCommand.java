package io.github.aluria.kingdoms.commands.kingdom;

import io.github.aluria.engine.utils.Players;
import io.github.aluria.kingdoms.KingdomsPlugin;
import io.github.aluria.kingdoms.enums.role.RolePermission;
import io.github.aluria.kingdoms.models.invite.Invite;
import io.github.aluria.kingdoms.models.kingdom.Kingdom;
import io.github.aluria.kingdoms.models.kingdom.KingdomMember;
import io.github.aluria.kingdoms.models.kingdom.KingdomUser;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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

        final KingdomUser user = plugin.getUserRegistry().getByName(player.getName());
        if(!user.hasKingdom()) {
            player.sendMessage("§cVocê não faz parte de um reino. Crie um utilizando o comando /reino criar.");
            return;
        }

        final Kingdom kingdom = plugin.getKingdomRegistry().getById(user.getKingdomId());

        final KingdomMember member = kingdom.getMember(user.getPlayerName());
        if(!member.hasPermission(RolePermission.INVITE_MEMBER)) {
            player.sendMessage("§cVocê não possui permissão suficiente para fazer isto.");
            return;
        }

        if(!kingdom.canInvite()) {
            player.sendMessage("§cO reino no qual você faz parte já atingiu o máximo de membros.");
            return;
        }

        final Player target = Players.getPlayer(args[0]);
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
        /*target.sendMessage("§aVocê recebeu um convite para entrar no reino [" + kingdom.getTag() + "] " + kingdom.getName() + ". " +
          "Para aceitar, utilize /reino entrar " + kingdom.getTag() + ".");*/

        target.spigot().sendMessage(newComponent(kingdom));
    }

    private TextComponent newComponent(Kingdom kingdom) {
        final TextComponent base = new TextComponent("");

        final TextComponent info = new TextComponent(TextComponent.fromLegacyText(
          "Você recebeu um convite para entrar no reino [" + kingdom.getTag() + "] " + kingdom.getName() + ". "
        ));
        info.setColor(ChatColor.GREEN);

        info.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(
          "§7[" + kingdom.getTag() + "] " + kingdom.getName() + "\n " +
            "§7Reputação: " + kingdom.getTotalReputation() + "\n " +
            "§7Nível: " + kingdom.getLevel() + "\n " +
            "§7Membros: " + kingdom.getOnlineMembers().size() + "/" + kingdom.getMembers().size()
        )));

        base.addExtra(info);

        final TextComponent accept = new TextComponent(TextComponent.fromLegacyText(
          "§aPara aceitar basta clicar §a§lAQUI§a."
        ));
        accept.setColor(ChatColor.GREEN);

        accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(
          "§a/reino entrar " + kingdom.getTag()
        )));
        accept.setClickEvent(
          new ClickEvent(ClickEvent.Action.RUN_COMMAND,
            "/reino entrar " + kingdom.getTag()
          )
        );

        base.addExtra(accept);
        
        return base;
    }
}
