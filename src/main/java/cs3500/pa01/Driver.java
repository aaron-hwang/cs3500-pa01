package cs3500.pa01;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Comparator;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - args[0]: Absolute/Relative path towards directory with desired .md files,
   *               args[1]: Desired ordering of files
   *               args[2]: Desired Absolute/relative path of output file
   */
  public static void main(String[] args) throws IOException {
    System.out.println("Hello from PA01 Template Repo");
    //initialize input and output
    Path inputPath;
    Path outputPath;

    //Initialize markdown compiler
    MarkdownCompiler compiler = new MarkdownCompiler();

    //Initialize our file traverser and collection
    FileTraverser ft = new FileTraverser();
    FileCollection filesToCompile;

    //Initialize our comparator
    Comparator<TraversedFile> ordering;
    String fileOrder = args[1];
    FileOrdering order = FileOrdering.valueOf(fileOrder.toUpperCase());

    //Determine the file ordering we should use
    switch (order) {
      case FILENAME:
        ordering = new FilesByName();
        break;
      case CREATED:
        ordering = new FilesByCreatedDate();
        break;
      case MODIFIED:
        ordering = new FilesByModifiedTime();
        break;
      default:
        throw new IllegalArgumentException("Please input a valid file ordering.");
    }

    //Initialize the input and output path
    try {
      inputPath = Path.of(args[0]);
    } catch (InvalidPathException exception) {
      throw new InvalidPathException(args[0],"That is not a valid input path");
    }

    try {
      outputPath = Path.of(args[2]);
    } catch (InvalidPathException e) {
      throw new InvalidPathException(args[2], "Invalid output path");
    }

    //Initialize and walk our file tree
    try {
      Files.walkFileTree(inputPath, ft);
    } catch (IOException e) {
      throw new IOException("Could not write to file");
    }

    //Create and write to our output file
    File output = new File(outputPath + ".md");
    try {
      output.createNewFile();
    } catch (IOException e) {
      throw new IOException("Could not write to file");
    }

    //Create a file writer, attempt to write to a new file
    FileWriter writeToOutput;

    try {
      writeToOutput = new FileWriter(output);
    } catch (IOException e) {
      throw new IOException("Could not initialize file writer");
    }

    filesToCompile = ft.getVisitedFiles();
    filesToCompile.sort(ordering);

    try {
      writeToOutput.write(compiler.compileCollection(filesToCompile));
      writeToOutput.close();
    } catch (IOException e) {
     throw new IOException("Could not write to file");
    }



  }
}