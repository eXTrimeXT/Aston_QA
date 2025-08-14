import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleAreaCalculatorTest {
    @Test
    void testAreaWithBaseAndHeight() {
        assertEquals(10.0, TriangleAreaCalculator.calculateArea(5, 4));
    }

    @Test
    void testAreaWithInvalidBaseOrHeight() {
        assertThrows(IllegalArgumentException.class, () -> TriangleAreaCalculator.calculateArea(-5, 4));
        assertThrows(IllegalArgumentException.class, () -> TriangleAreaCalculator.calculateArea(5, -4));
    }

    @Test
    void testAreaWithHeronFormula() {
        assertEquals(6.0, TriangleAreaCalculator.calculateAreaHeron(3, 4, 5), 0.001);
    }

    @Test
    void testAreaWithInvalidTriangleSides() {
        assertThrows(IllegalArgumentException.class, () -> TriangleAreaCalculator.calculateAreaHeron(1, 2, 3));
    }
}