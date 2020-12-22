package io.github.aluria.engine.database;

import io.github.aluria.engine.database.builder.delete.DeleteBuilder;
import io.github.aluria.engine.database.builder.insert.InsertBuilder;
import io.github.aluria.engine.database.builder.select.SelectBuilder;
import io.github.aluria.engine.database.builder.update.ReplaceBuilder;
import io.github.aluria.engine.database.builder.update.UpdateBuilder;

public class Queries {


  public static SelectBuilder selectFrom(String table) {
    return new SelectBuilder(table);
  }

  public static ReplaceBuilder replaceInto(String table) {
    return new ReplaceBuilder(table);
  }

  public static InsertBuilder insertInto(String table) {
    return new InsertBuilder(table);
  }

  public static DeleteBuilder deleteFrom(String table) {
    return new DeleteBuilder(table);
  }

  public static UpdateBuilder update(String table) {
    return new UpdateBuilder(table);
  }

}
