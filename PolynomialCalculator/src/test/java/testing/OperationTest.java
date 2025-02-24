package testing;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.MathematicalOperations;
import model.DivisionResult;
import model.Polynomial;

public class OperationTest {

    @Test
    public void testAdd() {
        Polynomial poly1 = new Polynomial();
        // Add terms to poly1

        Polynomial poly2 = new Polynomial();
        // Add terms to poly2

        Polynomial expectedSum = new Polynomial();
        // Add expected terms to expectedSum

        Polynomial actualSum = MathematicalOperations.add(poly1, poly2);
        assertEquals(expectedSum, actualSum);
    }

    // Write similar test methods for other operations (subtract, multiply, divide, derive, integrate)
}

