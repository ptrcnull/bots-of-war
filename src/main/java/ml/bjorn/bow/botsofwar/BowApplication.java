package ml.bjorn.bow.botsofwar;

import ml.bjorn.bow.botsofwar.Map;
import ml.bjorn.bow.botsofwar.utils.FileUtils;
import ml.bjorn.bow.botsofwar.utils.PathUtils;

import java.util.Arrays;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BowApplication {
	static final Logger log = LoggerFactory.getLogger(BowApplication.class);
	static MapHolder mapHolder = new MapHolder();
	public static void main(String[] args) {
		SpringApplication.run(BowApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			API api = new API(restTemplate);
			
			Map map = new Map(api.getMap());
			BowApplication.mapHolder.setMap(map);
			log.info(map.toString());

			AStarPathFinder pathFinder = new AStarPathFinder(map, 1000, false);
			Path path = pathFinder.findPath(null,  0,  0,  6,  7);
			log.info(map.toString(path));

			log.info(Arrays.toString(PathUtils.getMovesListFromPath(path)));
			FileUtils.saveFile("./index.html", map.toHTMLTemplate());

			map.updateCoordinates(api.getCoordinates());
			log.info(map.toString());

			map.updateEntities(api.getEntities());
			log.info(map.toString());
		};
	}

  static class MapHolder {
    private Map map;
    MapHolder() {}
    public Map getMap() { return this.map; }
    public void setMap(Map map) { this.map = map; }
  }
}

