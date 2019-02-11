package ml.bjorn.bow.botsofwar;

import java.util.HashSet;

// Yeah, it's not a database. I know.

class Database {
  static HashSet<String> values = new HashSet<String>();
  public static void insert (String value) {
    values.add(value);
  }
  public static HashSet<String> getValues () {
    return values;
  }
}