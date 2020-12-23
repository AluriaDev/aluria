package io.github.aluria.engine.database.type.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.aluria.engine.database.type.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

public final class HikariConnectionProvider extends ConnectionProvider {

  private final HikariDataSource source;

  public HikariConnectionProvider(String jdbcUrl, String userName, String password) {
    this.source = createDataSource(jdbcUrl, userName, password);
  }

  @Override
  public Connection getCurrentConnection() {
    try {
      return source.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException("Falha ao obter a conexão do hikari.");
    }
  }

  private HikariDataSource createDataSource(String jdbcUrl, String username, String password) {
    final HikariConfig config = new HikariConfig();

    config.setMaximumPoolSize(Runtime.getRuntime().availableProcessors() * 2);
    config.setJdbcUrl(jdbcUrl);
    config.setUsername(username);
    config.setPassword(password);

    config.addDataSourceProperty("autoReconnect", "true");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("useServerPrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "256");

    return new HikariDataSource(config);
  }

}
