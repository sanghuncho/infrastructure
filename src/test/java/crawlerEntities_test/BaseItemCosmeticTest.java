package crawlerEntities_test;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import crawlerEntities.BaseItemCosmetic;
import util.Formatter;

public class BaseItemCosmeticTest {
    @Spy
    private BaseItemCosmetic baseItemCosmetic;
    
    @Rule 
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    @Test
    public void calculatePriceWonTest() {
       baseItemCosmetic = Mockito.mock(BaseItemCosmetic.class, Mockito.CALLS_REAL_METHODS);
       //System.out.println(baseItemCosmetic.calculatePriceWon(100));
    }
    
    @Test
    public void calculatePriceWonWithExtraFeeTest() {
       baseItemCosmetic = Mockito.mock(BaseItemCosmetic.class, Mockito.CALLS_REAL_METHODS);
       System.out.println(baseItemCosmetic.calculatePriceWonWithExtraFee(3.89, 200));
       System.out.println(baseItemCosmetic.calculatePriceWonWithExtraFee(6.79, 500));
    }
    
    @Test
    public void getItemFullNameHtmlTest() {
        // assert statements
       //System.out.println(BaseItemCosmetic.getItemFullNameHtml("gkoo"));
    }
    
    @Test
    public void getItemDescriptionHtmlTest() {
        // assert statements
       String st = "Das darin enthaltene Avocadoöl verleiht der Haut Elastizität und Spannkraft. Die Pflege erfrischt und belebt die müden Augen und zieht rasch in die Haut ein.";
       //System.out.println(BaseItemCosmetic.getItemDescriptionHtml(Formatter.setLinebreakAfterPunctHtml(st)));
    }
}
