package io.github.aluria.game.entity.player.upgrade;

import io.github.aluria.engine.utils.text.Lore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import static io.github.aluria.engine.utils.text.Lore.emptyLore;
import static io.github.aluria.engine.utils.text.Lore.of;

@Getter
@AllArgsConstructor
public enum GamerUpgrade {

  COMBAT("Habilidades de Combate", 250, Material.IRON_SWORD, of(
    "§7Aumenta seu dano base em §b+1",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  SAVAGERY("Selvageria", 450, new MaterialData(351, (byte) 1), of(
    "§7Aumenta sua chance de acerto",
    "§7critico em §b+1%.",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  DEFENDER("Defensor", 250, Material.CHAINMAIL_CHESTPLATE, of(
    "§7Aumenta a sua defesa base",
    "§7em §b+1",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  WISDOM("Sabedoria", 350, Material.BOOK, emptyLore()),
  SKILL_MASTERY("Maestria", 1000, Material.NETHER_STAR, emptyLore()),
  DODGE("Esquiva", 300, Material.FEATHER, emptyLore()),
  PRECISION("Precisão", 300, Material.ARROW, emptyLore());


  private String displayName;
  private int experienceCostPerLevel;
  private MaterialData iconData;
  private Lore lore;

  GamerUpgrade(String displayName, int experienceCostPerLevel, Material iconData, Lore lore) {
    this.displayName = displayName;
    this.experienceCostPerLevel = experienceCostPerLevel;
    this.iconData = new MaterialData(iconData);
    this.lore = lore;
  }
}
