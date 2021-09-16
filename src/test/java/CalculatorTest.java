import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void AddTwoNumbers() {
        assertEquals(Calculator.add(""), 0, "Should return 0");
        assertEquals(Calculator.add("1"), 1, "Should return 1");
    }
}