package ml.bjorn.bow.botsofwar;

import java.util.HashSet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public void addGreeting(@RequestBody String greeting) {
    Database.insert(greeting);
  }
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public HashSet<String> getGreetings() {
    return Database.getValues();
  }
}
