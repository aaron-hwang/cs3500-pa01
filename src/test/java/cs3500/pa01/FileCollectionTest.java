package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileCollectionTest {
  public static final String sampleDirectory  = "src/test/resources";
  FileCollection collection;
  FileTraverser traverser;
  TraversedFile sample;
  FileCollection empty;
  FileTime knownCreationTime;
  FileTime knownModifiedTime;

  /**
   * Setup for each test method
   */
  @BeforeEach
  public void setup() {
    traverser = new FileTraverser();
    empty = new FileCollection();
    try {
      Files.walkFileTree(Path.of(sampleDirectory), traverser);
    } catch (IOException e) {
      fail();
    }
    collection = traverser.getVisitedFiles();
    knownCreationTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
    knownModifiedTime = FileTime.from(Instant.parse("2023-05-15T12:00:00Z"));
    Path amazing = Path.of("amazing.md");
    sample = new TraversedFile(knownCreationTime, knownModifiedTime, amazing);
  }

  /**
   * Tests whether a file collection is properly sorted
   */
  @Test
  void testSort() {
    collection.sort(new FilesByName());
    assertEquals(collection.getFileList().get(0).getRawFile().getFileName(),
        Path.of("amazing.md"));
  }

  /**
   * Tests whether the add function properly adds an instance of a TraversedFile
   */
  @Test
  void testAdd() {
    empty.add(sample);
    assertEquals(collection.getFileList().size(), traverser.getVisitedFiles().getFileList().size());
    assertEquals(empty.getFileList().size(), 1);
  }

  /**
   * Tests whether the getter for a file collection properly works
   */
  @Test
  void testGetFileList() {
    assertEquals(empty.getFileList().size(), 0);
  }
}