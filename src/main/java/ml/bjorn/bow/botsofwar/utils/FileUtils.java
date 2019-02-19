package ml.bjorn.bow.botsofwar.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
  public static String readFile(String path) {
    List<String> lines;
    try {
      lines = Files.readAllLines(Paths.get(path));
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
      lines = Arrays.asList(new String[]{});
    }
    return String.join("\n", lines);
  }

	public static void saveFile(String path, String content) {
		List<String> lines = Arrays.asList(content.split("[\\r\\n]+"));
		try {
			Files.write(Paths.get(path), lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}