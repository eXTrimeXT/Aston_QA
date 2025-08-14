import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticOperationsTest {
    @Test
    void testAddition() {
        assertEquals(5, ArithmeticOperations.add(2, 3));
    }

    @Test
    void testSubtraction() {
        assertEquals(-1, ArithmeticOperations.subtract(2, 3));
    }

    @Test
    void testMultiplication() {
        assertEquals(6, ArithmeticOperations.multiply(2, 3));
    }

    @Test
    void testDivision() {
        assertEquals(0.666, ArithmeticOperations.divide(2, 3), 0.001);
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> ArithmeticOperations.divide(2, 0));
    }
}