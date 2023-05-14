package cs3500.pa01;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A class to represent and interact with a collection of files
 */
public class FileCollection {
  private ArrayList<Path> fileList;

  public FileCollection() {
    this.fileList = new ArrayList<>();
  }


  /**
   * Sort this collection by a given order
   *
   * @param order - the method by which to sort this collection
   */
  public void sort(Comparator<Path> order) {
    this.fileList.sort(order);
  }

  /**
   * Add a given file to this collection
   *
   * @param file - The file being added to this collection
   */
  public void add(Path file) {
    this.fileList.add(file);
  }

  /**
   * Compiles the contents of this file collection into a string
   * @return Returns a string representig
   */
  public String compileTo() {
    StringBuilder stringBuild = new StringBuilder();
    Pattern header = Pattern.compile(
        "/#{1,6}.+(?=\\n)/g");
    Pattern important = Pattern.compile("[[.]]");

    for (Path f : this.fileList) {
      Scanner fileScan = null;
      try {
        fileScan = new Scanner(f.toFile());
      } catch (FileNotFoundException e) {
        System.err.print("Could not find that file");
      }

      while (fileScan.hasNextLine()) {
        stringBuild.append(fileScan.nextLine());
        stringBuild.append("\n");

      }
    }

    return stringBuild.toString();
  }






}
