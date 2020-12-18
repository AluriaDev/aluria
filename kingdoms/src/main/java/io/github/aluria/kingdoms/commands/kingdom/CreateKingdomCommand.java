package io.github.aluria.kingdoms.commands.kingdom;

import io.github.aluria.kingdoms.KingdomsPlugin;
import io.github.aluria.kingdoms.enums.role.Role;
import io.github.aluria.kingdoms.models.kingdom.Kingdom;
import io.github.aluria.kingdoms.models.kingdom.KingdomMember;
import io.github.aluria.kingdoms.validators.kingdom.KingdomNameValidator;
import io.github.aluria.kingdoms.validators.kingdom.KingdomTagValidator;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

public class CreateKingdomCommand {

    private static final KingdomNameValidator NAME_VALIDATOR = new KingdomNameValidator();
    private static final KingdomTagValidator TAG_VALIDATOR = new KingdomTagValidator();

    private final KingdomsPlugin plugin;

    public CreateKingdomCommand(KingdomsPlugin plugin) {
        this.plugin = plugin;
    }

    public void onCommand(Player player, String label, String[] args) {
        if(args.length < 2) {
            player.sendMessage("§cUtilize: /" + label + " criar <tag> <nome>.");
            return;
        }

        if(plugin.getKingdomRegistry().getInRange(player.getLocation(), 100).size() > 0) {
            player.sendMessage("§cVocê não pode criar um reino nesse local pois existe um reino à menos de 100 blocos de distância.");
            return;
        }

        final KingdomMember member = plugin.getMemberRegistry().getByName(player.getName());
        if(member == null) return;

        if(member.hasKingdom()) {
            player.sendMessage("§cVocê já faz parte de um reino.");
            return;
        }

        final String tag = args[0].toUpperCase();
        if(!TAG_VALIDATOR.validate(tag)) {
            player.sendMessage("§cA tag do seu reino deve conter apenas alfanuméricos e possuir o tamanho de 2 à 4 carácteres.");
            return;
        }

        if (plugin.getKingdomRegistry().getByTag(tag) != null) {
            player.sendMessage("§aJá existe um reino com a tag citada.");
            return;
        }

        final String name = String.join(
          " ",
          Arrays.copyOfRange(args, 1, args.length)
        );
        if(!NAME_VALIDATOR.validate(name)) {
            player.sendMessage("§cO nome do seu reino deve conter apenas alfanuméricos e possuir o tamanho de 3 à 12 carácteres.");
            return;
        }

        if(plugin.getKingdomRegistry().getByName(name) != null) {
            player.sendMessage("§aJá existe um reino com o nome citado.");
            return;
        }

        final Kingdom kingdom = new Kingdom(UUID.randomUUID(), tag, name, player.getLocation());
        kingdom.addMember(member);

        member.setKingdomId(kingdom.getId());
        member.setRole(Role.KING);

        plugin.getKingdomRegistry().put(kingdom);

        player.sendMessage("§aO reino [" + kingdom.getTag() + "] " + kingdom.getName() + " foi criado com sucesso.");

    }
}
