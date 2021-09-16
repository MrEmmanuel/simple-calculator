import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void AddTwoNumbers() {
        assertEquals(Calculator.add(""), 0, "Should return 0");
        assertEquals(Calculator.add("1"), 1, "Should return 1");
    }

    @Test
    public void AddMultipleNumbers() {
        assertEquals(Calculator.add("1,2,3,4"), 10, "Should return 10");

    }

    @Test
    public void AddNewLines() {
        assertEquals(6, Calculator.add("1\n2,3"));
    }

    @Test
    public void AddDifferentDelimiters() {
        assertEquals(3, Calculator.add("//;\n1;2"));
        assertEquals(3, Calculator.add("//4\n142"));
    }

    @Test
    public void AddNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.add("-1,-2,3,4"), "Should throw since the are negative numbers");
    }

    @Test
    public void AddIgnoreGreaterOrEqualThan1000() {
        assertEquals(3, Calculator.add("//;\n1000;1;2"));
    }

    @Test
    public void AddAnyLengthDelimiter() {
        assertEquals(6, Calculator.add("//***\n1***2***3"));
    }

    @Test
    public void AddAnyDelimiterAndLength() {
        assertEquals(6, Calculator.add("//[:D][%]\n1:D2%3"));
        assertEquals(6, Calculator.add("//[***][%%%]\n1***2%%%3"));
    }
}