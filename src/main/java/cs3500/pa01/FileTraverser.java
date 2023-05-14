package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

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
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    this.haveVisited = true;
    System.out.format("Beginning traversal of directory %s%n", dir);
    return CONTINUE;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    this.haveVisited = true;
    if (attrs.isRegularFile() && file.getFileName().toString().endsWith(".md")) {
      this.visitedFiles.add(file);

    }
    return CONTINUE;
  }

  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    this.haveVisited = true;
    System.err.println(exc);
    return CONTINUE;
  }

  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
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

}
