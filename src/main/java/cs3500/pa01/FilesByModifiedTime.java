package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

/**
 * A comparator to compare files by their last modified time
 */
public class FilesByModifiedTime implements Comparator<Path> {
  @Override
  public int compare(Path o1, Path o2) {
    BasicFileAttributes o1Atts = null;
    BasicFileAttributes o2Atts = null;

    try {
      o1Atts = Files.readAttributes(o1, BasicFileAttributes.class);
      o2Atts = Files.readAttributes(o2, BasicFileAttributes.class);
    } catch (IOException e) {
      System.err.println("It seems there was en error with the IO.");
      e.printStackTrace();
      System.exit(1);
    }

    return o1Atts.lastModifiedTime().compareTo(o2Atts.lastModifiedTime());
  }
}
