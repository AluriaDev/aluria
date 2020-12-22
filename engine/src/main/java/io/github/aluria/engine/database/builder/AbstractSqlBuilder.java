package io.github.aluria.engine.database.builder;


import java.util.List;

public abstract class AbstractSqlBuilder {

  protected void appendList(StringBuilder sql, List<?> list, String init, String sep) {

    boolean first = true;

    for (Object s : list) {
      if (first) {
        sql.append(init);
      } else {
        sql.append(sep);
      }
      sql.append(s);
      first = false;
    }
  }

}