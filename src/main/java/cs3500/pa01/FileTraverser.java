package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * A class that implements FileTraverser, for the purpose of walking our file tree
 */
public class FileTraverser implements FileVisitor<Path> {
  private FileCollection visitedFiles;
  private boolean haveVisited;

  public FileTraverser() {
    this.visitedFiles = new FileCollection();
    this.haveVisited = false;
  }


  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    this.haveVisited = true;
    this.successHandler("Beginning traversal of directory %s%n", dir);
    return CONTINUE;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    this.haveVisited = true;
    if (attrs.isRegularFile() && file.getFileName().toString().endsWith(".md")) {
      TraversedFile traveled =
          new TraversedFile(attrs.creationTime(), attrs.lastModifiedTime(), file);
      this.visitedFiles.add(traveled);

    }
    return CONTINUE;
  }

  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    this.haveVisited = true;
    this.errorHandler("Could not visit file");
    return CONTINUE;
  }

  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    this.haveVisited = true;
    return CONTINUE;
  }

  /**
   * Return all the files visited by this file
   * @return A FileCollection representing all files visited by this traverser.
   */
  public FileCollection getVisitedFiles() {
    if (!this.haveVisited) {
      throw new IllegalStateException("Cannot compile when file traverser has not yet traversed");
    }
    else {
      return this.visitedFiles;
    }
  }

  /**
   * Method for handling when a file visit is unsuccesssful
   * @param errorMessage the message we want to throw
   * @return Returns the error message
   * @throws IOException when there is an issue with IO
   */
  public String errorHandler(String errorMessage) throws IOException {
    System.err.println(errorMessage);
    throw new IOException(errorMessage);
  }

  public String successHandler(String successMessage, Path dir) {
    System.out.format(successMessage,dir);
    return successMessage;
  }



}
