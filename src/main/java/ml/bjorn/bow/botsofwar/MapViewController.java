package ml.bjorn.bow.botsofwar;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapViewController {
  @GetMapping("/map")
  public String map(Model model) {
    model.addAttribute("map", BowApplication.mapHolder.getMap().toHTML());
    return "map";
  }
  @GetMapping("/mapWithPath")
  public String mapWithPath(
    @RequestParam(name="fromX", required=true) int fromX,
    @RequestParam(name="fromY", required=true) int fromY,
    @RequestParam(name="toX", required=true) int toX,
    @RequestParam(name="toY", required=true) int toY,
    Model model
  ) {
    Map map = BowApplication.mapHolder.getMap();
		AStarPathFinder pathFinder = new AStarPathFinder(map, 1000, false);
		Path path = pathFinder.findPath(null, fromX, fromY, toX, toY);
    model.addAttribute("map", map.toHTML(path));
    return "map";
  }

}