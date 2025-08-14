import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaCalculatorNGTest {
    @Test
    public void testAreaWithBaseAndHeight() {
        assertEquals(TriangleAreaCalculator.calculateArea(5, 4), 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAreaWithInvalidBase() {
        TriangleAreaCalculator.calculateArea(-5, 4);
    }

    @Test
    public void testAreaWithHeronFormula() {
        assertEquals(TriangleAreaCalculator.calculateAreaHeron(3, 4, 5), 6.0, 0.001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAreaWithInvalidTriangleSides() {
        TriangleAreaCalculator.calculateAreaHeron(1, 2, 3);
    }
}