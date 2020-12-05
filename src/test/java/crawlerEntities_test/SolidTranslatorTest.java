package crawlerEntities_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import translator.SolidTranslator;

public class SolidTranslatorTest {
    // before init 
    
    @Test
    public void getBrandNameKorTest() {
        // assert statements
        SolidTranslator.initBrandTranslator();
        assertEquals("must be 로고나", SolidTranslator.getBrandNameKor("logona"), "로고나");
        assertEquals("must be 로고나", SolidTranslator.getBrandNameKor("LOGONA"), "로고나");
        assertEquals("must be logon", SolidTranslator.getBrandNameKor("logon"), "logon");
    }
    
    @Test
    public void hasBrandNameKorTest() {
        // assert statements
        SolidTranslator.initBrandTranslator();
        assertTrue("must be true", SolidTranslator.hasBrandNameKor("logona"));
        assertFalse("must be false", SolidTranslator.hasBrandNameKor("logo"));
    }
}
