package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TraversedFileTest {
  TraversedFile sample1;
  TraversedFile sample2;
  FileCollection collection;

  /**
   * Setup for each test
   */
  @BeforeEach
  public void setup() {
    FileTime knownCreationTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
    FileTime knownModifiedTime = FileTime.from(Instant.parse("2023-05-15T12:00:00Z"));
    Path amazing = Path.of("src/test/resources/amazing.md");
    FileTime knownCreationTime1 = FileTime.from(Instant.parse("2023-05-12T12:00:00Z"));
    FileTime knownModifiedTime1 = FileTime.from(Instant.parse("2023-05-16T12:00:00Z"));
    Path among = Path.of("src/test/resources/among.md");
    sample1 = new TraversedFile(knownCreationTime, knownModifiedTime, amazing);
    sample2 = new TraversedFile(knownCreationTime1, knownModifiedTime1, among);
    ArrayList<TraversedFile> list = new ArrayList<>(Arrays.asList(sample1, sample2));
    collection = new FileCollection(list);
  }

  /**
   * Test whether we can properly grab the raw file data
   */
  @Test
  public void testGetRawFile() {
    assertEquals(sample1.getRawFile(), Path.of("src/test/resources/amazing.md"));
    assertEquals(sample2.getRawFile(), Path.of("src/test/resources/among.md"));
  }

  /**
   * test the method compareModifiedTime
   */
  @Test
  public void testCompareModifiedTime() {
    assertTrue(sample1.compareModifiedTime(sample2) < 0);
    assertFalse(sample2.compareModifiedTime(sample1) < 0);
  }

  /**
   * test the method compareCreatedTime
   */
  @Test
  public void testCompareCreationTime() {
    assertTrue(sample1.compareCreatedTime(sample2) > 0);
    assertFalse(sample2.compareCreatedTime(sample1) > 0);
  }

  /**
   * Test the method compareName
   */
  @Test
  public void testCompareName() {
    assertTrue(sample1.compareName(sample2) < 0);
    assertFalse(sample2.compareName(sample1) < 0);
  }






}