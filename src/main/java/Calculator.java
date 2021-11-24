import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Calculator {
    public static void main(String[] args){

        System.out.println(Calculator.add("1,2,3,4"));
        System.out.println(Calculator.add("//4\n142"));
        System.out.println(Calculator.add("//***\n1***2***3"));
        System.out.println(Calculator.add("//[(-_-')][%]\n1(-_-')2%3"));
        System.out.println(Calculator.add("//[abc][777][:(]\n1abc27773:(1"));

    }
    private String delimiter;
    private String numbers;
    private Calculator(String delimiter, String numbers) {
        this.delimiter = delimiter;
        this.numbers = numbers;
    }
    private int sum() {
        noInvalidInput();
        noNegativeNumbers();
        return getNumbers().sum();
    }


    private void noNegativeNumbers() {
        String negativeNumberSequence = getNumbers().filter(n -> n < 0)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));
        if (!negativeNumberSequence.isEmpty()) {
            throw new IllegalArgumentException("\nERROR: negatives not allowed " + negativeNumberSequence);
        }
    }

    private IntStream getNumbers() {
        if (numbers.isEmpty()) {
            return IntStream.empty();
        } else {
            return Stream.of(numbers.split(delimiter))
                    .mapToInt(Integer::parseInt)
                    .map(n -> n % 1000);
        }
    }
    public static int add(String input) {
        return parseInput(input).sum();
    }
    private static Calculator parseInput(String input) {
        if (input.startsWith("//")) {
            String[] headerAndNumberSequence = input.split("\n", 2);
            String delimiter = parseDelimiter(headerAndNumberSequence[0]);
            return new Calculator(delimiter, headerAndNumberSequence[1]);
        } else {
            return new Calculator(",|\n", input);
        }
    }
    private static String parseDelimiter(String str) {
        String delimiter = str.substring(2);
        if (delimiter.startsWith("[")) {
            delimiter = delimiter.substring(1, delimiter.length() - 1);
        }
        return Stream.of(delimiter.split("]\\["))
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }
    private void noInvalidInput(){
        if(!numbers.isEmpty() && numbers.length() >6) {
            if(!Character.isDigit(numbers.charAt(numbers.length() - 1)) || numbers.startsWith(" ") || numbers.substring(4,6).equalsIgnoreCase("3//")){
                throw new IllegalArgumentException("ERROR: invalid input");
            }
        }
    }
}
