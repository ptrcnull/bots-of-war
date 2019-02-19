package ml.bjorn.bow.botsofwar;

import ml.bjorn.bow.botsofwar.BotMap;
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
			
			BotMap map = new BotMap(api.getMap());
			BowApplication.mapHolder.setMap(map);
			log.info(map.toString());
			AStarPathFinder pathFinder = new AStarPathFinder(map, 1000, false);
			Path path = pathFinder.findPath(null,  0,  0,  6,  7);
			log.info(map.toString(path));
			log.info(Arrays.toString(PathUtils.getMovesListFromPath(path)));
			FileUtils.saveFile("./index.html", map.toHTMLTemplate());
	
			Entity[] entities = api.getEntites();
			log.info(entities[0].toString());
		};
	}

}

