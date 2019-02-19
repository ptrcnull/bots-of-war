package ml.bjorn.bow.botsofwar;


import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class BotMap implements TileBasedMap {
	public Integer[][] map;
	public static String[] blockTypes = {"dirt", "grass", "water", "stone", "base", "mine"};

	public BotMap(Integer[][] m) {
		this.map = rotateMap(m);
	}

	public static Integer[][] rotateMap(Integer[][] map) {
		Integer[][] newMap = new Integer[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			newMap[i] = map[map.length - 1 - i];
		}
		return newMap;
	}
	
	public Integer getBlock(int x, int y) {
		return map[y][x];
	}
	
	public String getBlockType(int x, int y) {
		return blockTypes[getBlock(x, y)];
	}

	public String getBlockHTMLImageTag(int x, int y) {
		return "<img src=\"https://botsofwar.bjorn.ml/static/img/" + getBlockType(x, y) + ".png\">";
	}
	
	public String toString() {
		String str = "\n";
		for(Integer[] row : map) {
			str += "[";
			for (Integer field : row) {
				str += String.valueOf(field);
			}
			str += "]\n";
		}
		return str;
	}
	
	public String toStringWithPath(Path path) {
		String str = "\n";
		for(int y = 0; y < map.length; y++) {
			str += "[";
			for (int x = 0; x < map[0].length; x++) {
				Integer tile = map[y][x];
				str += path.contains(x,  y) ? " " : String.valueOf(tile);
			}
			str += "]\n";
		}
		return str;
	}

	@Override
	public int getWidthInTiles() {
		return map[0].length;
	}

	@Override
	public int getHeightInTiles() {
		return map.length;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
	}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty) {
		Integer block = map[ty][tx];
		return !(block == 0 || block == 1);
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		return 0;
	}
}
