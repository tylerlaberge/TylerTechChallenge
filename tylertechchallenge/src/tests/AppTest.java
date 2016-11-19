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
import java.util.ArrayList;
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
    private List<Path> getInputFilePaths(int task) throws Exception {
        List<Path> input_file_paths = new ArrayList<>();
        if (task == 1){
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1a_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1b_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1c_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1d_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1e_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1f_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1g_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1h_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1i_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1j_input.txt").toURI()));

        }
        else if (task == 2){
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input2a.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input2b.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input2c.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input2d.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input2e.txt").toURI()));
        }
        else if (task == 3){
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input3.txt").toURI()));
        }
        else if (task == 4){
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input4a.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input4b.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input4c.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input4d.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/input4e.txt").toURI()));
        }
        return input_file_paths;
    }
    private List<Path> getOutputFilePaths(int task) throws Exception {
        List<Path> output_file_paths = new ArrayList<>();
        if (task == 1){
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1a_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1b_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1c_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1d_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1e_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1f_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1g_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1h_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1i_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1j_output.txt").toURI()));
        }
        else if (task == 2){
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output2a.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output2b.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output2c.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output2d.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output2e.txt").toURI()));
        }
        else if (task == 3){
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output3.txt").toURI()));
        }
        else if (task == 4){
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output4a.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output4b.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output4c.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output4d.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/output4e.txt").toURI()));
        }
        return output_file_paths;
    }
    private void testTask(List<Path> input_file_paths, List<Path> output_file_paths) throws Exception {
        for (int i = 0; i < input_file_paths.size(); i++) {
            Path input_file_path = input_file_paths.get(i);
            Path output_file_path = output_file_paths.get(i);

            String[] args = new String[2];
            args[0] = input_file_path.toString();
            args[1] = this.folder.getRoot() + "/output.txt";

            System.out.println(input_file_path.toString());
            System.out.println(output_file_path.toString());

            App.main(args);

            Path expected_output_file_path = FileSystems.getDefault().getPath(output_file_path.toString());
            Path actual_output_path = FileSystems.getDefault().getPath(args[1]);

            List<String> expected_lines = Files.readAllLines(expected_output_file_path, StandardCharsets.UTF_8);
            List<String> actual_lines = Files.readAllLines(actual_output_path, StandardCharsets.UTF_8);

            assertEquals(expected_lines, actual_lines);
        }
    }
    @Test
    public void testTaskOneInput() throws Exception {
        List<Path> input_file_paths = this.getInputFilePaths(1);
        List<Path> output_file_paths = this.getOutputFilePaths(1);

        this.testTask(input_file_paths, output_file_paths);
    }
    @Test
    public void testTaskTwoInput() throws Exception {
        List<Path> input_file_paths = this.getInputFilePaths(2);
        List<Path> output_file_paths = this.getOutputFilePaths(2);

        this.testTask(input_file_paths, output_file_paths);
    }
    @Test
    public void testTaskThreeInput() throws Exception {
        List<Path> input_file_paths = this.getInputFilePaths(3);
        List<Path> output_file_paths = this.getOutputFilePaths(3);

        this.testTask(input_file_paths, output_file_paths);
    }
    @Test
    public void testTaskFourInput() throws Exception {
        List<Path> input_file_paths = this.getInputFilePaths(4);
        List<Path> output_file_paths = this.getOutputFilePaths(4);

        this.testTask(input_file_paths, output_file_paths);
    }
}