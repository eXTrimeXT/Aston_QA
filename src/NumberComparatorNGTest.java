import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorNGTest {
    @Test
    public void testCompareGreater() {
        assertEquals(NumberComparator.compare(5, 3), "5 is greater than 3");
    }

    @Test
    public void testCompareEqual() {
        assertEquals(NumberComparator.compare(5, 5), "5 is equal to 5");
    }
}