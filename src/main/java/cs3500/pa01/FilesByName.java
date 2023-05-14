package cs3500.pa01;

import java.nio.file.Path;
import java.util.Comparator;

/**
 * A comparator class to order files by their name
 */
public class FilesByName implements Comparator<Path> {

  @Override
  public int compare(Path o1, Path o2) {
    String o1Name = o1.getFileName().toString();
    String o2Name = o2.getFileName().toString();
    return o1Name.compareTo(o2Name);
  }
}
