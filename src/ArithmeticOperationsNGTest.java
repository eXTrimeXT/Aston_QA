import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticOperationsNGTest {
    @Test
    public void testAddition() {
        assertEquals(ArithmeticOperations.add(2, 3), 5);
    }

    @Test
    public void testDivision() {
        assertEquals(ArithmeticOperations.divide(2, 3), 0.666, 0.001);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        ArithmeticOperations.divide(2, 0);
    }
}