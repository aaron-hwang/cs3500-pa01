package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilesByModifiedTimeTest {

  FileCollection collection;
  TraversedFile sample1;
  TraversedFile sample2;

  /**
   * Setup for tests
   */
  @BeforeEach
  public void setup() {
    FileTime knownCreationTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
    FileTime knownModifiedTime = FileTime.from(Instant.parse("2023-05-15T12:00:00Z"));
    Path amazing = Path.of("amazing.md");
    FileTime knownCreationTime1 = FileTime.from(Instant.parse("2023-05-12T12:00:00Z"));
    FileTime knownModifiedTime1 = FileTime.from(Instant.parse("2023-05-16T12:00:00Z"));
    Path among = Path.of("more/among.md");
    sample1 = new TraversedFile(knownCreationTime, knownModifiedTime, amazing);
    sample2 = new TraversedFile(knownCreationTime1, knownModifiedTime1, among);
    ArrayList<TraversedFile> list = new ArrayList<>(Arrays.asList(sample1, sample2));
    collection = new FileCollection(list);
  }

  /**
   * Tests whether this comparator orders files properly by modified date
   */
  @Test
  public void testOrder() {
    collection.sort(new FilesByModifiedTime());
    assertEquals(collection.getFileList().get(0), sample1);
    assertEquals(collection.getFileList().get(1), sample2);
  }


}