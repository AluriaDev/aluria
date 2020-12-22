package io.github.aluria.game.player.upgrade;

import io.github.aluria.engine.utils.inventory.ItemBuilder;
import io.github.aluria.engine.utils.text.Lore;
import io.github.aluria.game.player.Gamer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import static io.github.aluria.engine.utils.text.Lore.of;
import static io.github.aluria.engine.utils.text.RomanNumbers.toRoman;

@Getter
@AllArgsConstructor
public enum UpgradeType {

  COMBAT("Habilidades de Combate", 500, Material.IRON_SWORD, of(
    "§7Aumenta o seu §fdano base §7em §b+1",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  SAVAGERY("Selvageria", 750, new MaterialData(351, (byte) 1), of(
    "§7Aumenta a sua chance de §facerto",
    "§fcritico §7em §b+1%.",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  DEFENDER("Defensor", 500, Material.CHAINMAIL_CHESTPLATE, of(
    "§7Aumenta a sua §fdefesa base§7 em §b+1",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  VITALITY("Vitalidade" , 750 , Material.GOLDEN_APPLE , of(
    "§7Aumenta sua §fvida maxima §7em §b+25",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  WISDOM("Sabedoria", 750, Material.BOOK, of(
    "§7Aumenta o seu §fganho de experiência",
    "§7de todas as as fontes em §b+10%",
    "",
    "§7Custo: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  SKILL_MASTERY("Maestria", 2500, Material.NETHER_STAR, of(
    "§7Aumenta o limite de nível das suas",
    "§fhabilidades passivas §7em §b+10",
    "",
    "§7Custo: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  DODGE("Esquiva", 300, Material.FEATHER, of(
    "§7Aumenta a sua chance de esquivar",
    "§7de ataques em §b+1%",
    "",
    "§7Custo: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  PRECISION("Precisão", 300, Material.ARROW, of(
    "§7Aumenta sua chance de acertar",
    "§7ataques em §b+1%",
    "",
    "§7Custo: §a<cost> exp",
    "",
    "§eClique para melhorar."
  ));


  private String displayName;
  private int experienceCostPerLevel;
  private MaterialData iconData;
  private Lore lore;

  UpgradeType(String displayName, int experienceCostPerLevel, Material iconData, Lore lore) {
    this.displayName = displayName;
    this.experienceCostPerLevel = experienceCostPerLevel;
    this.iconData = new MaterialData(iconData);
    this.lore = lore;
  }

  public int getExpCost(int upgradeLevel) {
    return experienceCostPerLevel * upgradeLevel;
  }

  public ItemStack toItemStack(Gamer gamer) {
    int upgradeLevel = gamer.getUpgradeMap().getUpgradeLevel(this);

    return new ItemBuilder(iconData)
      .name("§d" + displayName + " " + toRoman(upgradeLevel))
      .lore(lore.clone()
        .ident(1)
        .replace("<cost>", (upgradeLevel + 1))
      )
      .build();
  }
}
