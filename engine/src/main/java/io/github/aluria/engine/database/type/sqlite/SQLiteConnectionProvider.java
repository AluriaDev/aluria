package io.github.aluria.engine.database.type.sqlite;

import io.github.aluria.engine.database.type.ConnectionProvider;
import lombok.SneakyThrows;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public final class SQLiteConnectionProvider extends ConnectionProvider {

  private static final String SQLITE_URL_FILE_MODEL = "jdbc:sqlite:";

  private Plugin plugin;
  private String databaseName;

  protected Connection connection;

  public SQLiteConnectionProvider(Plugin plugin, String databaseName) {
    this.plugin = plugin;
    this.databaseName = databaseName;
  }

  @SneakyThrows
  @Override
  public Connection getCurrentConnection() {
    if (connection != null && !connection.isClosed()) {
      return connection;
    }

    connection = this.connect();
    if (connection != null) {
      return connection;
    }

    throw new NullPointerException("Provided connection is null.");
  }

  private Connection connect() {
    try {
      // pqp bukkit vsf
      Class.forName("org.sqlite.JDBC");

      File file = this.getDatabaseFile();

      return DriverManager.getConnection(SQLITE_URL_FILE_MODEL + file.getPath());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private File getDatabaseFile() throws IOException {
    String fileName = databaseName;
    if (!fileName.endsWith(".db")) {
      fileName += ".db";
    }

    File mainDir = plugin.getDataFolder();
    mainDir.mkdirs();

    File file = new File(mainDir, fileName);
    if (!file.exists()) {
      file.createNewFile();
    }

    return file;
  }

  @Override
  protected void finalize() throws Throwable {
    connection.close();
    connection = null;

    super.finalize();
  }
}
