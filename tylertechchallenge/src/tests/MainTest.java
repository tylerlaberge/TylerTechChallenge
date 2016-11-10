package tests;

import org.junit.*;

import com.tylerlaberge.main.Main;
import org.junit.rules.TemporaryFolder;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void main() throws Exception {

        String[] args = new String[2];
        URL input_file_url = MainTest.class.getResource("input_files/input1.txt");
        URL output_file_url = MainTest.class.getResource("output_files/output1.txt");
        Path input_file_path = Paths.get(input_file_url.toURI());
        Path output_file_path = Paths.get(output_file_url.toURI());

        args[0] = input_file_path.toString();
        args[1] = this.folder.getRoot() + "/output.txt";

        Main.main(args);

        Path expected_output_file_path = FileSystems.getDefault().getPath(output_file_path.toString());
        Path actual_output_path = FileSystems.getDefault().getPath(args[1]);

        List<String> expected_lines = Files.readAllLines(expected_output_file_path, StandardCharsets.UTF_8);
        List<String> actual_lines = Files.readAllLines(actual_output_path, StandardCharsets.UTF_8);

        assertEquals(expected_lines, actual_lines);
    }
}