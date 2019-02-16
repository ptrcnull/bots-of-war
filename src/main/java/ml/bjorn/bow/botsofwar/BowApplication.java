package ml.bjorn.bow.botsofwar;

import ml.bjorn.bow.botsofwar.BotMap;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BowApplication {
    static final Logger log = LoggerFactory.getLogger(BowApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BowApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();
        Integer[][] m = restTemplate.getForObject("http://bow.westeurope.cloudapp.azure.com:8080/getMap", Integer[][].class);
        BotMap map = new BotMap(m);
        log.info(map.toString());
        AStarPathFinder pathFinder = new AStarPathFinder(map, 1000, false);
        Path path = pathFinder.findPath(null,  0,  0,  6,  7);
        for (int i = 0; i < path.getLength(); i++) {
        	System.out.println(
        		path.getStep(i).getX() +
        		", " +
        		path.getStep(i).getY()
			);
        }
        log.info(map.toStringWithPath(path));
	}

}

