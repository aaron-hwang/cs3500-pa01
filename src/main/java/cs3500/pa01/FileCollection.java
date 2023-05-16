package cs3500.pa01;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class to represent and interact with a collection of files
 */
public class FileCollection {
  private ArrayList<TraversedFile> fileList;

  /**
   * Default constructor
   */
  public FileCollection() {
    this.fileList = new ArrayList<>();
  }

  /**
   * Convenience constructor
   *
   * @param traveled The arraylist of traversed file to pass in to instantiate with
   */
  public FileCollection(ArrayList<TraversedFile> traveled) {
    this.fileList = traveled;
  }

  /**
   * Sort this collection by a given order
   *
   * @param order - the method by which to sort this collection
   */
  public void sort(Comparator<TraversedFile> order) {
    this.fileList.sort(order);
  }

  /**
   * Add a given file to this collection
   *
   * @param file - The file being added to this collection
   */
  public void add(TraversedFile file) {
    this.fileList.add(file);
  }

  /**
   * Get the raw arraylist of file paths
   *
   * @return this collection's filelist
   */
  public ArrayList<TraversedFile> getFileList() {
    return this.fileList;
  }

}
