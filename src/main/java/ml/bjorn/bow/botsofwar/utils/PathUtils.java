package ml.bjorn.bow.botsofwar.utils;

import org.newdawn.slick.util.pathfinding.Path;

public class PathUtils {
	public static String[] getMovesListFromPath(Path path) {
		String arr[] = new String[path.getLength() - 1];
		for (int i = 0; i < path.getLength() - 1; i++) {
			arr[i] = getStepDirection(path, i, false);
		}
		return arr;
	}

	private static String getStepDirection(Path path, int step, boolean useArrows) {
		String arrows[] = {"↑", "↓", "←", "→"};
		String letters[] = {"U", "D", "L", "R"};
		String signs[] = useArrows ? arrows : letters;
		int oldX = path.getStep(step).getX();
		int oldY = path.getStep(step).getY();
		int newX = path.getStep(step + 1).getX();
		int newY = path.getStep(step + 1).getY();
		if (oldY > newY) return signs[1]; // 0/1 switched due to map being reversed
		if (oldY < newY) return signs[0];
		if (oldX > newX) return signs[2];
		if (oldX < newX) return signs[3];
		return "";
	}
}