package ml.bjorn.bow.botsofwar.utils;

import java.util.ArrayList;
import java.util.List;

import ml.bjorn.bow.botsofwar.Tile;

public class MapUtils {
  public static List<List<Tile>> integerToTileMap(List<List<Integer>> map) {
    List<List<Tile>> tileMap = new ArrayList<List<Tile>>();
    for (List<Integer> row : map) {
      List<Tile> tileRow = new ArrayList<Tile>();
      for (Integer tile : row) {
        tileRow.add(new Tile(tile));
      }
      tileMap.add(tileRow);
    }
    return tileMap;
  }
}