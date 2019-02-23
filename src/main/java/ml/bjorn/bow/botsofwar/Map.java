package ml.bjorn.bow.botsofwar;

import ml.bjorn.bow.botsofwar.utils.FileUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

@FunctionalInterface
interface TileResolver {
	String resolve(int x, int y);
}

public class Map implements TileBasedMap {
	public List<List<Tile>> map;

	public Map(List<List<Tile>> m) {
		this.map = m;
		Collections.reverse(map);
	}

	public void updateCoordinates(List<Coordinates> coordinatesList) {
		for (List<Tile> row : map) {
			for (Tile tile : row) {
				tile.clearCoordinates();
			}
		}
		for (Coordinates coordinates : coordinatesList) {
			getTile(coordinates.getX(), coordinates.getY()).markCoordinates();
		}
	}

	public void updateEntities(List<Entity> entitiesList) {
		for (List<Tile> row : map) {
			for (Tile tile : row) {
				tile.setEntity(null);
			}
		}
		for (Entity entity : entitiesList) {
			getTile(entity.getCoordinates().getX(), entity.getCoordinates().getY()).setEntity(entity);
		}
	}

	public Tile getTile(int x, int y) {
		return map.get(y).get(x);
	}

	private String toString(TileResolver tileResolver, Boolean useHTML) {
		ArrayList<String> rows = new ArrayList<>();
		for (int y = 0; y < getHeightInTiles(); y++) {
			String str = useHTML ? "<div class=\"row\">" : "[";
			for (int x = 0; x < getWidthInTiles(); x++) {
				str += tileResolver.resolve(x, y);
			}
			str += useHTML ? "</div>" : "]";
			rows.add(str);
		}
		Collections.reverse(rows);
		return "\n" + String.join("\n", rows);
	}
	
	public String toString() {
		return toString((x, y) -> getTile(x, y).getConsoleValue(), false);
	}
	
	public String toString(Path path) {
		return toString((x, y) -> path.contains(x,  y) ? " " : getTile(x, y).getConsoleValue(), false);
	}

	public String toHTML() {
		return toString((x, y) -> getTile(x, y).getHTMLImageTag(), true);
	}

	public String toHTML(Path path) {
		return toString((x, y) -> getTile(x, y).getHTMLImageTag(path.contains(x, y)), true);
	}

	public String toHTMLTemplate() {
		String template = FileUtils.readFile("./src/main/resources/template.html");
		return template.replace("%body%", String.join("\n", toHTML()));
	}

	@Override
	public int getWidthInTiles() {
		return map.get(0).size();
	}

	@Override
	public int getHeightInTiles() {
		return map.size();
	}

	@Override
	public void pathFinderVisited(int x, int y) {}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty) {
		return getTile(tx, ty).isObstacle();
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		return 0;
	}
}
