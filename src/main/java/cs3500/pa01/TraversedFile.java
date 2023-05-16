package cs3500.pa01;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class TraversedFile {
  private FileTime createdTime;
  private FileTime modified;
  Path rawFile;

  //Constructor
  public TraversedFile(FileTime created, FileTime modified, Path rawFile) {
    this.createdTime = created;
    this.modified = modified;
    this.rawFile = rawFile;
  }

  /**
   * Gets the raw file data
   * @return the File data of this traversed file
   */
  public Path getRawFile() {
    return rawFile;
  }

  /**
   * Compares the modified time of this traversed file to another traversed file
   * @param other the other traversed file to compare to
   * @return an int >1 if greater than, 0 if equal, <0 if less than
   */
  public int compareModifiedTime(TraversedFile other) {
    return this.modified.compareTo(other.modified);
  }

  /**
   * Compares the creation time of this traversed file to another file
   * @param other the other traversed file to compare to
   * @return an int >1 if greater than, 0 if equal, <0 if less than
   */
  public int compareCreatedTime(TraversedFile other) {
    return this.createdTime.compareTo(other.createdTime);
  }

  /**
   * Compares the name of this file to a given file
   * @param other the other traversed file to compare to
   * @return an int >1 if greater than, 0 if equal, <0 if less than
   */
  public int compareName(TraversedFile other) {
    return this.rawFile.getFileName().compareTo(other.rawFile.getFileName());
  }

}