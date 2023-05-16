package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverTest {

  String[] invalidOrder;
  String[] invalidInputPath;
  String[] invalidOutputPath;
  String[] invalidOutputPath1;
  String[] validInput;
  String[] badOutputPath;
  String[] ioexceptionPath;
  String[] ioexceptionPathFirstArgFail;

  /**
   * Setup for tests
   */
  @BeforeEach
  public void setup() {
    ioexceptionPath =
        new String[]{"src/test/resources", "filename", "pa01-aaronhwang/outputFolder/output"};
    ioexceptionPathFirstArgFail =
        new String[]{"pa01-aaronhwang", "modified", "outputFolder/output"};
    validInput = new String[] {"src/test/resources", "filename", "outputFolder/output"};
    badOutputPath = new String[] {"src/test/resources", "filename", "src/test/resources"};
    invalidOrder = new String[] {"src/test/resources", "thisisbad", "src/test/resources/output"};
    invalidInputPath =
        new String[] {"src/test/more/:'ä'jimmbob", "created", "src/test/resources/more/output"};
    invalidOutputPath = new String[] {"src/test/resources", "modified", "src/test/resourcesä:::"};
    invalidOutputPath1 = new String[] {"src/test/resources", "filename", "src/test/resourcesä:::"};
  }

  /**
   * Test whether main throws the correct exception when given an incorrect ordering
   */
  @Test
  public void testFileOrderingInput() {
    assertThrows(IllegalArgumentException.class,
        () -> Driver.main(invalidOrder));
  }

  /**
   * Test whether our main properly handles being given improper file pathings
   */
  @Test
  public void testValidPaths() {
    try {
      assertThrows(InvalidPathException.class, () -> Driver.main(invalidInputPath));
    } catch (Exception e) {
      fail();
    }
    try {
      assertThrows(InvalidPathException.class, () -> Driver.main(invalidOutputPath));
    } catch (Exception e) {
      fail();
    }
    try {
      assertThrows(InvalidPathException.class, () -> Driver.main(invalidOutputPath1));
    } catch (Exception e) {
      fail();
    }
    try {
      assertThrows(IOException.class,
          () -> Driver.main(ioexceptionPath));
    } catch (Exception e) {
      fail();
    }
    try {
      assertThrows(IOException.class,
          () -> Driver.main(ioexceptionPathFirstArgFail));
    } catch (Exception e) {
      fail();
    }

  }

  @Test
  public void testWritingException() {

  }

  @Test
  public void testWritingSuccess() {
    try {
      Driver.main(validInput);
    } catch (Exception e) {
      fail();
    }
    assertTrue(Files.exists(Path.of("outputFolder/output.md")));
  }



}