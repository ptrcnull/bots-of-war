package ml.bjorn.bow.botsofwar;

import org.springframework.web.client.RestTemplate;

public class API {
  private RestTemplate restTemplate;
  private final String BASE_URL = "http://bow.westeurope.cloudapp.azure.com:8080";
  public API(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  Integer[][] getMap() {
    return restTemplate.getForObject(BASE_URL + "/getMap", Integer[][].class);
  }

  Integer[][] getCoordinates() {
    return restTemplate.getForObject(BASE_URL + "/getCoordinatesList", Integer[][].class);
  }

  Entity[] getEntites() {
    return restTemplate.getForObject(BASE_URL + "/getUnitList", Entity[].class);
  }
}