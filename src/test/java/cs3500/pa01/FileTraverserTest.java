package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileTraverserTest {
  FileTraverser ft = new FileTraverser();
  FileTraverser ft0 = new FileTraverser();
  FileCollection fc;

  /**
   * Setup for each test
   */
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
   * Test the getVisitedFiles method
   */
  @Test
  void testGetVisitedFiles() {
    assertEquals(ft.getVisitedFiles().getFileList(), fc.getFileList());
    assertThrows(IllegalStateException.class,
        () -> ft0.getVisitedFiles());
  }

  /**
   * Test error handler
   */
  @Test
  public void testErrorHandler() {
    assertThrows(IOException.class,
        () -> ft.errorHandler("Error message"));
  }

  /**
   * Test success handler
   */
  @Test
  public void testSuccessHandler() {
    assertEquals(ft.successHandler("test message",
        Path.of("src/test/resources/among.md")),
        "test message");
  }

}