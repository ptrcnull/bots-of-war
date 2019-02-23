package ml.bjorn.bow.botsofwar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import ml.bjorn.bow.botsofwar.utils.MapUtils;
import ml.bjorn.bow.botsofwar.utils.Utils;

public class API {
  private RestTemplate restTemplate;
  private final String BASE_URL = "http://bow.westeurope.cloudapp.azure.com:8080";
  public API(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  List<List<Tile>> getMap() {
    Integer[][] map = restTemplate.getForObject(BASE_URL + "/getMap", Integer[][].class);
    return MapUtils.integerToTileMap(Utils.twoDarrayTo2DList(map));
  }

  List<Coordinates> getCoordinates() {
    Integer[][] coordinates = restTemplate.getForObject(BASE_URL + "/getCoordinatesList", Integer[][].class);
    List<Coordinates> list = new ArrayList<Coordinates>();
    for (Integer[] coords : coordinates) {
      list.add(new Coordinates(coords));
    }
    return list;
  }

  List<Entity> getEntities() {
    Entity[] entities = restTemplate.getForObject(BASE_URL + "/getUnitList", Entity[].class);
    return Arrays.asList(entities);
  }
}