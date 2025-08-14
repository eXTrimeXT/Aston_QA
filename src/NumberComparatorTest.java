import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberComparatorTest {
    @Test
    void testCompareGreater() {
        assertEquals("5 is greater than 3", NumberComparator.compare(5, 3));
    }

    @Test
    void testCompareLess() {
        assertEquals("3 is less than 5", NumberComparator.compare(3, 5));
    }

    @Test
    void testCompareEqual() {
        assertEquals("5 is equal to 5", NumberComparator.compare(5, 5));
    }
}