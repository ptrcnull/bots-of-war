package ml.bjorn.bow.botsofwar.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
  public static <T>List<List<T>> twoDarrayTo2DList(T[][] array) {
    List<List<T>> list = new ArrayList<List<T>>();
    for (T[] row : array) {
      list.add(Arrays.asList(row));
    }
    return list;
  }
}