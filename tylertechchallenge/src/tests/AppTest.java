package tests;

import org.junit.*;

import com.tylerlaberge.main.App;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
    }
    @After
    public void tearDown() throws Exception {
    }
    private Path getInputFilePath(int task) throws Exception {
        Path input_file_path = null;
        if (task == 1){
            input_file_path = Paths.get(AppTest.class.getResource("input_files/input1.txt").toURI());
        }
        else if (task == 2){
            input_file_path = Paths.get(AppTest.class.getResource("input_files/input2.txt").toURI());
        }
        else if (task == 4){
            input_file_path = Paths.get(AppTest.class.getResource("input_files/input4.txt").toURI());
        }
        return input_file_path;
    }
    private Path getOutputFilePath(int task) throws Exception {
        Path output_file_path = null;
        if (task == 1){
            output_file_path = Paths.get(AppTest.class.getResource("output_files/output1.txt").toURI());
        }
        else if (task == 2){
            output_file_path = Paths.get(AppTest.class.getResource("output_files/output2.txt").toURI());
        }
        else if (task == 4){
            output_file_path = Paths.get(AppTest.class.getResource("output_files/output4.txt").toURI());
        }
        return output_file_path;
    }
    @Test
    public void testTaskOneInput() throws Exception {

        Path input_file_path = this.getInputFilePath(1);
        Path output_file_path = this.getOutputFilePath(1);

        String[] args = new String[2];
        args[0] = input_file_path.toString();
        args[1] = this.folder.getRoot() + "/output.txt";

        App.main(args);

        Path expected_output_file_path = FileSystems.getDefault().getPath(output_file_path.toString());
        Path actual_output_path = FileSystems.getDefault().getPath(args[1]);

        List<String> expected_lines = Files.readAllLines(expected_output_file_path, StandardCharsets.UTF_8);
        List<String> actual_lines = Files.readAllLines(actual_output_path, StandardCharsets.UTF_8);

        assertEquals(expected_lines, actual_lines);
    }
    @Test
    public void testTaskTwoInput() throws Exception {

        Path input_file_path = this.getInputFilePath(2);
        Path output_file_path = this.getOutputFilePath(2);

        String[] args = new String[2];
        args[0] = input_file_path.toString();
        args[1] = this.folder.getRoot() + "/output.txt";

        App.main(args);

        Path expected_output_file_path = FileSystems.getDefault().getPath(output_file_path.toString());
        Path actual_output_path = FileSystems.getDefault().getPath(args[1]);

        List<String> expected_lines = Files.readAllLines(expected_output_file_path, StandardCharsets.UTF_8);
        List<String> actual_lines = Files.readAllLines(actual_output_path, StandardCharsets.UTF_8);

        assertEquals(expected_lines, actual_lines);
    }

    @Test
    public void testTaskFourInput() throws Exception {

        Path input_file_path = this.getInputFilePath(4);
        Path output_file_path = this.getOutputFilePath(4);

        String[] args = new String[2];
        args[0] = input_file_path.toString();
        args[1] = this.folder.getRoot() + "/output.txt";

        App.main(args);

        Path expected_output_file_path = FileSystems.getDefault().getPath(output_file_path.toString());
        Path actual_output_path = FileSystems.getDefault().getPath(args[1]);

        List<String> expected_lines = Files.readAllLines(expected_output_file_path, StandardCharsets.UTF_8);
        List<String> actual_lines = Files.readAllLines(actual_output_path, StandardCharsets.UTF_8);

        assertEquals(expected_lines, actual_lines);
    }
}