package tests;

import com.tylerlaberge.main.App;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.TemporaryFolder;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AppTest {


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private List<Path> getInputFilePaths(int task) throws Exception {
        List<Path> input_file_paths = new ArrayList<>();
        if (task == 1) {
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
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1k_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1l_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1m_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1n_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task1_input_files/task1o_input.txt").toURI()));
        } else if (task == 2) {
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2a_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2b_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2c_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2d_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2e_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2f_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2g_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2h_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2i_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2j_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2k_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2l_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2m_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2n_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task2_input_files/task2o_input.txt").toURI()));
        } else if (task == 3) {
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3a_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3b_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3c_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3d_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3e_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3f_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3g_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3h_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3i_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3j_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3k_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3l_input.txt").toURI()));

            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3n_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task3_input_files/task3o_input.txt").toURI()));
        } else if (task == 4) {
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4a_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4b_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4c_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4d_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4e_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4f_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4g_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4h_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4i_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4j_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4k_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4l_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4m_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4n_input.txt").toURI()));
            input_file_paths.add(Paths.get(AppTest.class.getResource("input_files/task4_input_files/task4o_input.txt").toURI()));
        }
        return input_file_paths;
    }

    private List<Path> getOutputFilePaths(int task) throws Exception {
        List<Path> output_file_paths = new ArrayList<>();
        if (task == 1) {
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
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1k_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1l_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1m_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1n_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task1_output_files/task1o_output.txt").toURI()));
        } else if (task == 2) {
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2a_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2b_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2c_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2d_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2e_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2f_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2g_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2h_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2i_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2j_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2k_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2l_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2m_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2n_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task2_output_files/task2o_output.txt").toURI()));
        } else if (task == 3) {
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3a_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3b_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3c_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3d_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3e_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3f_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3g_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3h_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3i_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3j_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3k_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3l_output.txt").toURI()));

            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3n_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task3_output_files/task3o_output.txt").toURI()));
        } else if (task == 4) {
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4a_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4b_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4c_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4d_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4e_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4f_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4g_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4h_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4i_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4j_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4k_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4l_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4m_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4n_output.txt").toURI()));
            output_file_paths.add(Paths.get(AppTest.class.getResource("output_files/task4_output_files/task4o_output.txt").toURI()));
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
    private void testBadInput(Path input_file_path) {
        String[] args = new String[2];
        args[0] = input_file_path.toString();
        args[1] = this.folder.getRoot() + "/output.txt";

        System.out.println(input_file_path.toString());
        this.exit.expectSystemExit();
        App.main(args);
    }
    @Test
    public void testBadInputA() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_a.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }
    @Test
    public void testBadInputB() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_b.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }
    @Test
    public void testBadInputC() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_c.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }
    @Test
    public void testBadInputD() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_d.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputE() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_e.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputF() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_f.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputG() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_g.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputH() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_h.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputI() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_i.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputJ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_j.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputK() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_k.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputL() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_l.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputM() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_m.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputN() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_n.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputO() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_o.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputP() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_p.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputQ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_q.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputR() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_r.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputS() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_s.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputT() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_t.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputU() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_u.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputV() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_v.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputW() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_w.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputX() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_x.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }
    @Test
    public void testBadInputY() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_y.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }@Test
    public void testBadInputZ() throws Exception {
        Path input_file_path = Paths.get(AppTest.class.getResource("bad_input_files/bad_input_z.txt").toURI());
        this.exit.expectSystemExit();
        this.testBadInput(input_file_path);
    }
}