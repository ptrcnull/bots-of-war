package ml.bjorn.bow.botsofwar;

import ml.bjorn.bow.botsofwar.utils.FileUtils;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
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
	
	public String toString(Path path) {
		String str = "\n";
		for(int y = 0; y < map.length; y++) {
			str += "[";
			for (int x = 0; x < map[0].length; x++) {
				str += path.contains(x,  y) ? " " : String.valueOf(getBlock(x, y));
			}
			str += "]\n";
		}
		return str;
	}

	public String toHTMLTemplate() {
		String template = FileUtils.readFile("./src/main/resources/template.html");
		String str = "";
		for(int y = 0; y < map.length; y++) {
			str += "<div class=\"row\">";
			for (int x = 0; x < map[0].length; x++) {
				str += getBlockHTMLImageTag(x, y);
			}
			str += "</div>";
		}
		return template.replace("%body%", str);
	}

	public String toHTML() {
		String str = "";
		for(int y = 0; y < map.length; y++) {
			str += "<div class=\"row\">";
			for (int x = 0; x < map[0].length; x++) {
				str += getBlockHTMLImageTag(x, y);
			}
			str += "</div>";
		}
		return str;
	}

	public String toHTMLWithPath(int fromX, int fromY, int toX, int toY) {
		AStarPathFinder pathFinder = new AStarPathFinder(this, 1000, false);
		Path path = pathFinder.findPath(null, fromX, fromY, toX, toY);
		String str = "";
		for(int y = 0; y < map.length; y++) {
			str += "<div class=\"row\">";
			for (int x = 0; x < map[0].length; x++) {
				str += path.contains(x, y) ? "<img src=\"https://botsofwar.bjorn.ml/static/img/path.png\">" : getBlockHTMLImageTag(x, y);
			}
			str += "</div>";
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
	public void pathFinderVisited(int x, int y) {}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty) {
		Integer block = getBlock(tx, ty);
		return !(block == 0 || block == 1);
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		return 0;
	}
}
