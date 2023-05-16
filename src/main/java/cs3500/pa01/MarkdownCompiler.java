package cs3500.pa01;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Compiles markdown files
 */
public class MarkdownCompiler {

  private String importantFlagStart = "[[";
  private String importantFlagEnd = "]]";
  private String headerFlag = "#";

  /**
   * Compiles a FileCollection to a singular string containing the contents of
   * all files in the FileCollection
   * @return a string representing contents of all files, only returning the headers and "important"
   * content
   * @param collection the collection one wishes to compile
   */
  public String compileCollection(FileCollection collection) throws FileNotFoundException {
    StringBuilder build = new StringBuilder();
    ArrayList<TraversedFile> files;
    files = collection.getFileList();

    boolean foundContentYet = false;
    for (TraversedFile f : files) {
      try {
        build.append(this.compileFile(f));
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("One or more files could not be found");
      }
      foundContentYet = !this.compileFile(f).equals("");

      if (foundContentYet) {
        build.append("\n");
      }


    }

    return build.toString();
  }

  /**
   * Compile a singular markdown file
   * @param file The file we'd like to compilke
   * @return The important contents and headers of the file,as a String
   */
  public String compileFile(TraversedFile file) throws FileNotFoundException {
    StringBuilder stringBuild = new StringBuilder();
    Scanner fileScan;

    //Initialize scanner
    try {
      fileScan = new Scanner(file.getRawFile().toFile());
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File could not be found");
    }

    boolean flagImportantContentFound = false;

    while (fileScan.hasNextLine()) {
      String scan = fileScan.nextLine();
      int startIndex = 0;
      //If the line is not empty, scan it. Otherwise, just skip it.
      if (scan.length() > 0) {
        //If we find a header flag, append the whole line
        if (scan.substring(0, 1).equals(this.headerFlag)) {
          stringBuild.append(scan);
          stringBuild.append("\n");
        } else if (scan.contains(this.importantFlagStart)) {
          flagImportantContentFound = true;
          startIndex = scan.indexOf(importantFlagStart);
        }
        //If we find an important content flag, append up until we find the close.
        //This can carry over to multiple lines
        if (flagImportantContentFound) {
          int endIndex = scan.length();
          if (scan.contains(this.importantFlagEnd)) {
            flagImportantContentFound = false;
            endIndex = scan.indexOf(this.importantFlagEnd) + this.importantFlagEnd.length();
          }
          stringBuild.append(scan, startIndex, endIndex).append("\n");
        }
      }
    }
    return stringBuild.toString();
  }





}
