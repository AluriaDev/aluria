package io.github.aluria.game.entity.player.status;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GamerStatusType {

  HEALTH("Vida"),
  MAX_HEALTH("Vida Máxima"),

  MANA("Mana"),
  MAX_MANA("Mana Máxima"),

  STAMINA("Stamina"),
  MAX_STAMINA("Stamina Máxima");

  private String name;
}
