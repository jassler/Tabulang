import de.hskempten.tabulang.datatypes.InternalNumber;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InternalNumberTest {

    private final float f1 = -3.7f;
    private final InternalNumber n1 = new InternalNumber(f1);
    private final float f2 = 13.983f;
    private final InternalNumber n2 = new InternalNumber(f2);

    @Test
    public void testCreateNumber() {
        assertEquals(f1, n1.getFloatValue());
    }

    @Test
    public void testAdd() {
        assertEquals(f1 + f2, n1.add(n2).getFloatValue());
    }

    @Test
    public void testSubtract() {
        assertEquals(f1 - f2, n1.subtract(n2).getFloatValue());
    }

    @Test
    public void testMultiply() {
        assertEquals(f1 * f2, n1.multiply(n2).getFloatValue());
    }

    @Test
    public void testDivide() {
        DecimalFormat df = new DecimalFormat("0.000000");
        assertEquals(df.format(f1 / f2), df.format(n1.divide(n2).getFloatValue()));
    }

    @Test
    public void testDiff() {
        assertEquals(0.0f, n1.diff(n2).getFloatValue());
    }

    @Test
    public void testMod() {
        assertEquals(f1, n1.mod(n2).getFloatValue());
    }
}