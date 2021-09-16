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
}