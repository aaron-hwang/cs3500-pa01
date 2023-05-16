package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileTraverserTest {
  FileTraverser ft = new FileTraverser();
  FileTraverser ft0 = new FileTraverser();
  FileCollection fc;
  @BeforeEach
  public void setup() {
    try {
      Files.walkFileTree(Path.of("src/test/resources"), ft);
    } catch (IOException e) {
      fail();
    }
    fc = new FileCollection(ft.getVisitedFiles().getFileList());

  }

  /**
   * Tests preVisitDirectory
   */
  @Test
  void testPreVisitDirectory() {
    //assertEquals(ft.preVisitDirectory(), FileVisitResult.CONTINUE);
  }

  @Test
  void testVisitFile() {
  }

  @Test
  void testVisitFileFailed() {

  }

  @Test
  void testPostVisitDirectory() {
  }

  @Test
  void testGetVisitedFiles() {
    assertEquals(ft.getVisitedFiles().getFileList(), fc.getFileList());
    assertThrows(IllegalStateException.class,
        () -> ft0.getVisitedFiles());
  }

  @Test
  public void testErrorHandler() {
    assertThrows(IOException.class,
        () -> ft.errorHandler("Error message"));
  }

}