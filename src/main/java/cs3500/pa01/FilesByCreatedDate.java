package cs3500.pa01;

import java.util.Comparator;

/**
 * A comparator to compare files by their creation date
 */
public class FilesByCreatedDate implements Comparator<TraversedFile> {

  @Override
  public int compare(TraversedFile o1, TraversedFile o2) {
    return o1.compareCreatedTime(o2);
  }
}
