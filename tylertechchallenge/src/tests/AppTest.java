package tests;

import com.tylerlaberge.main.App;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AppTest {


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private final PrintStream orig_out_stream = System.out;
    private final PrintStream orig_err_stream = System.err;

    @After
    public void cleanUpStreams() {
        System.setOut(orig_out_stream);
        System.setErr(orig_err_stream);
    }

    private void testTask(Path input_file_path, Path output_file_path) throws IOException {
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

    private void testBadInput(Path input_file_path, String expected_output_message) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = new String[2];
        args[0] = input_file_path.toString();
        args[1] = this.folder.getRoot() + "/output.txt";

        this.exit.expectSystemExit();
        this.exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                assertEquals(expected_output_message + "\n".trim(), outContent.toString().trim());
            }
        });
        App.main(args);
    }

    @Test
    public void testTaskOneInputA() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1a_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1a_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputB() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1b_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1b_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputC() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1c_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1c_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputD() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1d_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1d_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputE() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1e_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1e_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputF() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1f_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1f_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputG() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1g_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1g_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputH() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1h_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1h_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputI() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1i_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1i_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputJ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1j_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1j_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputK() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1k_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1k_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputL() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1l_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1l_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputM() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1m_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1m_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputN() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1n_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1n_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskOneInputO() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1o_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1o_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputA() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2a_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2a_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputB() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2b_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2b_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputC() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2c_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2c_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputD() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2d_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2d_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputE() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2e_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2e_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputF() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2f_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2f_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputG() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2g_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2g_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputH() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2h_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2h_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputI() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2i_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2i_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputJ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2j_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2j_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputK() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2k_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2k_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputL() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2l_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2l_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputM() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2m_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2m_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputN() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2n_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2n_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskTwoInputO() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2o_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2o_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputA() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3a_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3a_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputB() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3b_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3b_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputC() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3c_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3c_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputD() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3d_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3d_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputE() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3e_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3e_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputF() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3f_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3f_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputG() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3g_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3g_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputH() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3h_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3h_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputI() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3i_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3i_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputJ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3j_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3j_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputK() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3k_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3k_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputL() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3l_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3l_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputN() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3n_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3n_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskThreeInputO() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3o_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3o_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputA() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4a_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4a_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputB() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4b_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4b_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputC() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4c_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4c_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputD() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4d_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4d_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputE() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4e_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4e_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputF() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4f_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4f_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputG() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4g_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4g_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputH() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4h_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4h_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputI() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4i_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4i_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputJ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4j_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4j_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputK() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4k_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4k_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputL() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4l_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4l_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputM() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4m_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4m_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputN() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4n_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4n_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testTaskFourInputO() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4o_input.txt").toURI());
        Path output_file_path = Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4o_output.txt").toURI());
        this.testTask(input_file_path, output_file_path);
    }

    @Test
    public void testBadInputA() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_a.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Invalid input file format.");
    }

    @Test
    public void testBadInputB() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_b.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputC() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_c.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputD() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_d.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputE() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_e.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputF() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_f.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputG() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_g.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputH() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_h.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Invalid input file format.");
    }

    @Test
    public void testBadInputI() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_i.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputJ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_j.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputK() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_k.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputL() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_l.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputM() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_m.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputN() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_n.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputO() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_o.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Invalid input file format.");
    }

    @Test
    public void testBadInputP() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_p.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputQ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_q.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputR() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_r.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputS() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_s.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputT() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_t.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputU() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_u.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputV() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_v.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Invalid input file format.");
    }

    @Test
    public void testBadInputW() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_w.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputX() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_x.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputY() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_y.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }

    @Test
    public void testBadInputZ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_z.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path, "Input file failed validation.");
    }
}