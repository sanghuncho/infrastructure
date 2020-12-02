package util_test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import util.Formatter;

public class FormatterTest {

    @Test
    public void splitAfterWordTest() {
        
        String example = "aa-word-bb";
        // assert statements
        assertEquals("must be aa-", Formatter.splitAfterWord(example, "word").get(0), "aa-");
        assertEquals("must be -bb", Formatter.splitAfterWord(example, "word").get(1), "-bb");
    }
}
