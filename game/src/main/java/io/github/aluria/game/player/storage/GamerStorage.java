package io.github.aluria.game.player.storage;

import io.github.aluria.engine.database.Queries;
import io.github.aluria.engine.database.type.ConnectionProvider;
import io.github.aluria.engine.gson.GsonFactory;
import io.github.aluria.engine.utils.inventory.Base64Utils;
import io.github.aluria.game.player.Gamer;
import io.github.aluria.game.player.skill.SkillMap;
import io.github.aluria.game.player.status.StatusMap;
import io.github.aluria.game.player.upgrade.UpgradeMap;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.intellij.lang.annotations.Language;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;

import static io.github.aluria.engine.utils.inventory.Base64Utils.toBase64List;

public final class GamerStorage {

  @Language("SQLite")
  private static final String CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS gamers (" +
      "player_name VARCHAR(16) PRIMARY KEY NOT NULL, " +
      "player_level INT NOT NULL DEFAULT 1," +
      "experience INT NOT NULL DEFAULT 0, " +
      "inventory TEXT NOT NULL, " +
      "last_location TEXT NOT NULL, " +
      "status_map TEXT NOT NULL, " +
      "upgrade_map TEXT NOT NULL, " +
      "skill_map TEXT NOT NULL, " +
      ")";

  private static final String UPDATE_ONE_SQL = Queries.replaceInto("gamers")
    .set("player_name", "?")
    .set("player_level", "?")
    .set("experience", "?")
    .set("inventory", "?")
    .set("status_map", "?")
    .set("upgrade_map", "?")
    .set("skill_map", "?")
    .toString();

  private static final String DELETE_ONE_SQL = Queries.deleteFrom("gamers")
    .where("player_name = ?")
    .toString();

  private static final String SELECT_ONE_SQL = Queries.selectFrom("gamers")
    .where("player_name = ?")
    .toString();


  private final ConnectionProvider provider;
  private final GsonFactory gsonFactory;

  public GamerStorage(ConnectionProvider provider) {
    this.provider = provider;
    this.gsonFactory = GsonFactory.getInstance();
  }

  public void createTable() {
    provider.execute(CREATE_TABLE_SQL);
  }

  public final int updateGamer(Gamer gamer) {
    return provider.executeUpdate(UPDATE_ONE_SQL, statement -> {
      statement.setString(1, gamer.getPlayerName().toLowerCase());
      statement.setInt(2, gamer.getLevel());
      statement.setInt(3, gamer.getExperience());
      statement.setString(4, toBase64List(gamer.getInventory()));
      statement.setString(5, gsonFactory.toJson(gamer.getLastLocation()));
      statement.setString(6, gsonFactory.toJson(gamer.getStatusMap()));
      statement.setString(7, gsonFactory.toJson(gamer.getUpgradeMap()));
      statement.setString(8, gsonFactory.toJson(gamer.getSkillMap()));
    });
  }

  public final int deleteGamer(Gamer gamer) {
    return provider.executeUpdate(
      DELETE_ONE_SQL,
      s -> s.setString(1, gamer.getPlayerName().toLowerCase())
    );
  }

  @Nullable
  public final Gamer selectGamer(String playerName) throws SQLException {
    ResultSet resultSet = provider.executeQuery(SELECT_ONE_SQL, s -> s.setString(1, playerName.toLowerCase()));
    if (resultSet == null || !resultSet.next()) {
      return null;
    }

    int level = resultSet.getInt("level");
    int experience = resultSet.getInt("experience");
    ItemStack[] inventory = Base64Utils.fromBase64List(resultSet.getString("inventory"));
    Location location = gsonFactory.fromJson(resultSet, "last_location", Location.class);
    StatusMap statusMap = gsonFactory.fromJson(resultSet, "status_map", StatusMap.class);
    UpgradeMap upgradeMap = gsonFactory.fromJson(resultSet, "upgrade_map", UpgradeMap.class);
    SkillMap skillMap = gsonFactory.fromJson(resultSet, "skill_map", SkillMap.class);

    return Gamer.builder()
      .playerName(playerName)
      .experience(experience)
      .inventory(inventory)
      .lastLocation(location)
      .statusMap(statusMap)
      .upgradeMap(upgradeMap)
      .skillMap(skillMap)
      .build();
  }

}
