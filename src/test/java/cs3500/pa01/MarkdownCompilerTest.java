package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarkdownCompilerTest {

  MarkdownCompiler mc = new MarkdownCompiler();
  FileTraverser ft = new FileTraverser();
  FileCollection fc = new FileCollection();
  FileCollection fc1;
  TraversedFile sampleInvalid;
  TraversedFile sampleValid;
  Path among;

  /**
   * Setup for each test
   */
  @BeforeEach
  public void setup() {
    among = Path.of("src/test/resources/more/among.md");
    try {
      Files.walkFileTree(Path.of("src/test/resources/more"), ft);
    } catch (IOException e) {
      fail();
    }
    FileTime knownCreationTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
    FileTime knownModifiedTime = FileTime.from(Instant.parse("2023-05-15T12:00:00Z"));
    Path burger = Path.of("src/test/resources/burger.md");
    fc = ft.getVisitedFiles();
    fc1 = new FileCollection();
    sampleInvalid = new TraversedFile(knownCreationTime, knownModifiedTime, burger);
    sampleValid = new TraversedFile(knownCreationTime, knownModifiedTime, among);

    fc1.add(sampleInvalid);

  }

  /**
   * Test compilation of a file collection
   */
  @Test
  void compileCollection() {
    assertThrows(FileNotFoundException.class,
        () -> mc.compileCollection(fc1));

    try {
      assertEquals(mc.compileCollection(fc).substring(0, 1), "#");
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * Test compiling a singular given TraversedFile
   */
  @Test
  void compileFile() {
    try {
      assertThrows(FileNotFoundException.class,
          () -> mc.compileFile(sampleInvalid));
    } catch (Exception e) {
      fail();
    }
    try {
      assertEquals(mc.compileFile(sampleValid).substring(0, 1), "#");
    } catch (Exception e) {
      fail();
    }

  }
}