package util_test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import util.Validator;

public class ValidatorTest {
    @Test
    public void getUniqueNameTest() {
        // assert statements
        assertEquals("must be ABBA", Validator.getUniqueName("ABBA"), "ABBA");
        assertEquals("must be ABBA_2", Validator.getUniqueName("ABBA"), "ABBA_2");
        assertEquals("must be ABBA_3", Validator.getUniqueName("ABBA"), "ABBA_3");
        assertEquals("must be KIA", Validator.getUniqueName("KIA"), "KIA");
        assertEquals("must be abba", Validator.getUniqueName("abba"), "abba");
    }
}
