package io.github.aluria.engine.database.type;

import io.github.aluria.engine.utils.function.UncheckedConsumer;
import io.github.aluria.engine.utils.function.UncheckedFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class ConnectionProvider {

  public abstract Connection getCurrentConnection();

  public boolean execute(String sql, UncheckedConsumer<PreparedStatement> consumer) {
    try (PreparedStatement statement = getCurrentConnection().prepareStatement(sql)) {
      if (consumer != null) {
        consumer.accept(statement);
      }

      return statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean execute(String sql) {
    return execute(sql, null);
  }

  public int executeUpdate(String sql, UncheckedConsumer<PreparedStatement> consumer) {
    try (PreparedStatement preparedStatement = getCurrentConnection().prepareStatement(sql)) {
      if (consumer != null) {
        consumer.accept(preparedStatement);
      }

      return preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int executeUpdate(String sql) {
    return executeUpdate(sql, null);
  }


  public ResultSet executeQuery(String sql, UncheckedConsumer<PreparedStatement> consumer) {
    try (PreparedStatement preparedStatement = getCurrentConnection().prepareStatement(sql)) {
      if (consumer != null) {
        consumer.accept(preparedStatement);
      }

      return preparedStatement.executeQuery();
    } catch (Exception e) {
      return null;
    }
  }

  public ResultSet executeQuery(String sql) {
    return executeQuery(sql, null);
  }
}
