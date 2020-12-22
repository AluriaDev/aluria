package io.github.aluria.engine.database.type.h2;

import io.github.aluria.engine.database.type.ConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;

public final class H2ConnectionProvider extends ConnectionProvider {

  private static final String H2_URL_CONNECTION_MODEL = "jdbc:h2:mem:";

  private String databaseName;

  public H2ConnectionProvider(String databaseName) {
    this.databaseName = databaseName;
  }

  @Override
  public Connection getCurrentConnection() {
    Connection connect = this.connect();
    if(connect == null){
      throw new NullPointerException("Provided connection is null.");
    }

    return connect;
  }

  private final Connection connect() {
    try {
      return DriverManager.getConnection(H2_URL_CONNECTION_MODEL + databaseName);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
