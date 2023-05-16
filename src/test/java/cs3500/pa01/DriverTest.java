package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.InvalidPathException;
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
  @BeforeEach
  public void setup() {
    ioexceptionPath =
        new String[]{"src/test/resources","filename","pa01-aaronhwang/outputFolder/output"};
    ioexceptionPathFirstArgFail =
        new String[]{"pa01-aaronhwang","modified", "outputFolder/output"};
      validInput = new String[] {"src/test/resources", "filename", "src/test/resources/output"};
      badOutputPath = new String[] {"src/test/resources", "filename", "src/test/resources"};
      invalidOrder = new String[] {"src/test/resources", "thisisbad", "src/test/resources/output"};
      invalidInputPath = new String[] {"src/test/::jimmbob", "created", "src/test/resources/output"};
      invalidOutputPath = new String[] {"src/test/resources", "modified", "src/test/resources:::"};
      invalidOutputPath1 = new String[] {"src/test/resources", "filename", "src/test/resources:::"};
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


}