package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.tylerlaberge.main.Main;
import static org.junit.Assert.*;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final String[] args = {"foo", "bar"};

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void main() throws Exception {
        Main.main(this.args);

        String output = this.outContent.toString().replaceAll("\n", "").replaceAll("\r", "");

        assertEquals("foobar", output);
        assertEquals("", this.errContent.toString());
    }

}