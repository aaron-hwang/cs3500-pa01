package cs3500.pa01;

import java.util.Comparator;

/**
 * A comparator class to order files by their name
 */
public class FilesByName implements Comparator<TraversedFile> {
  @Override
  public int compare(TraversedFile o1, TraversedFile o2) {
    return o1.compareName(o2);
  }
}
