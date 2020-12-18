package io.github.aluria.kingdoms.commands.kingdom;

import io.github.aluria.kingdoms.KingdomsPlugin;
import io.github.aluria.kingdoms.models.kingdom.Kingdom;
import io.github.aluria.kingdoms.models.kingdom.KingdomMember;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class KingdomCommand extends Command {

    private final KingdomsPlugin plugin;

    private final CreateKingdomCommand createCommand;
    private final InviteCommand inviteCommand;
    private final JoinCommand joinCommand;

    public KingdomCommand(KingdomsPlugin plugin) {
        super("reino");

        this.setAliases(Arrays.asList("reinos", "kingdom", "kingdoms", "k"));
        this.setUsage("reino ?");

        this.plugin = plugin;

        this.createCommand = new CreateKingdomCommand(plugin);
        this.inviteCommand = new InviteCommand(plugin);
        this.joinCommand = new JoinCommand(plugin);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando só pode ser utilizado em jogo.");
            return false;
        }

        final Player player = (Player) sender;
        if (args.length == 0) {

            final KingdomMember member = plugin.getMemberRegistry().getByName(player.getName());
            if(member == null) return false;

            if(!member.hasKingdom()) {
                player.sendMessage("§cVocê não faz parte de um reino. Crie um utilizando o comando /reino criar.");
                return false;
            }

            final Kingdom kingdom = plugin.getKingdomRegistry().getById(member.getKingdomId());

            // TODO: Abrir menu para gerenciar o reino

        } else {
            if(!parseSubCommand(player, label, args)) {
                player.sendMessage(helpTopic());
            }
        }

        return true;
    }

    /**
     * sim isso ta muito ruim.
     * achei meio merda fazer de outro jeito
     * e já abstrair em uns comandos básicos
     * se no futuro puder ter uma framework de comando
     * na engine seria melhor :)
     */

    private boolean parseSubCommand(Player player, String label, String[] args) {
        switch (args[0]) {
            case "criar":
            case "create":
                createCommand.onCommand(player, label, Arrays.copyOfRange(args, 1, args.length));
                break;

            case "convidar":
            case "invite":
                inviteCommand.onCommand(player, label, Arrays.copyOfRange(args, 1, args.length));
                break;

            case "entrar":
            case "join":
                joinCommand.onCommand(player, label, Arrays.copyOfRange(args, 1, args.length));
                break;

            default:
                return false;
        }

        return true;
    }

    private String[] helpTopic() {
        return new String[] {
          " ",
          "  §e§lREINOS",
          " ",
          " §e/reino criar <tag> <nome> §8- §7Criar um reino.",
          " §e/reino convidar <jogador> §8- §7Convidar um jogador para o reino.",
          " §e/reino entrar <tag> §8- §7Entrar para um reino.",
          " "
        };
    }
}
