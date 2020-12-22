package io.github.aluria.game.player.skill.category;

import io.github.aluria.engine.utils.inventory.ItemBuilder;
import io.github.aluria.engine.utils.text.Lore;
import io.github.aluria.game.player.skill.type.SkillType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum SkillCategory {


  COMBAT("Combate", Material.DIAMOND_SWORD, Lore.of(
    "",
    "§7Habilidades de combate contra",
    "§7entidades e jogadores.",
    "",
    "§eClique para ver as habilidades."
  )),
  MANUAL_JOB("Trabalhos Manuais", Material.BREAD, Lore.of(
    "",
    "§7Habilidades que envolvem trabalhos bracais.",
    "",
    "§eClique para ver as habilidades."
  )),
  MANUFACTURING("Fabricação", Material.ANVIL, Lore.of(
    "",
    "§7Criação de ferramentas, armas e items em geral.",
    "",
    "§eClique para ver as habilidades."
  ));

  private String name;
  private Material material;
  private Lore description;
  private ItemStack menuIcon;

  SkillCategory(String name, Material material, Lore description) {

    this.name = name;
    this.material = material;
    this.description = description;
    this.menuIcon = new ItemBuilder(material, 1)
      .name("§e" + name)
      .lore(description)
      .applyItemMeta(im -> im.removeItemFlags(ItemFlag.values()))
      .build();
  }

  public ItemStack getMenuIcon() {
    return menuIcon;
  }

  public String getName() {
    return name;
  }

  public Material getMaterial() {
    return material;
  }

  public Lore getDescription() {
    return description;
  }

  public ItemStack asItemStack() {
    return new ItemBuilder(material, 1)
      .name("§9" + name)
      .lore(description)
      .build();
  }
}
