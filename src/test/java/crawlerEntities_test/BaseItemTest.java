package crawlerEntities_test;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import crawlerEntities.BaseItem;

public class BaseItemTest {
    @Spy
    private BaseItem baseItem;
    
    @Rule 
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    @Test
    public void calculatePriceWonTest() {
       baseItem = Mockito.mock(BaseItem.class, Mockito.CALLS_REAL_METHODS);
       System.out.println(baseItem.calculatePriceWon(100));
    }
}
