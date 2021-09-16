public class Calculator {
    public static void main(String[] args){

        System.out.println(Calculator.add("1,2,3,4"));
        System.out.println(Calculator.add("//4\n142"));
        System.out.println(Calculator.add("-1,-2,3,4"));
        System.out.println(Calculator.add("//***\n1***2***3"));
        System.out.println(Calculator.add("//[abc][777][:(]\n1abc27773:(1"));

    }


    public static int add(String str) {
        String delimiter = ",\n";
        if (str.startsWith("//")) {
            delimiter += str.substring(2, str.indexOf("\n"));
            str = str.substring(str.indexOf("\n"));
        }
        return add(str, "[" + delimiter + "]");
    }

    static int add(String nums, String delimiter) {
        String[] arr = nums.split("[" + delimiter + "]");
        StringBuilder negativeNumbers = new StringBuilder();
        int sum = 0;
        try {
            for (String numberIndex : arr) {
                if (!numberIndex.trim().isEmpty() && (Character.isDigit(numberIndex.charAt(0)) || numberIndex.charAt(0) == '-')) {
                    if (!Character.isDigit(nums.charAt(nums.length() - 1))) {
                        throw new IllegalArgumentException("ERROR: invalid input");
                    }
                    if (Integer.parseInt(numberIndex.trim()) < 0) {
                        negativeNumbers.append(numberIndex.trim()).append(" ");
                    } else if (Integer.parseInt(numberIndex.trim()) < 1000) {
                        sum += Integer.parseInt(numberIndex.trim());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ERROR: invalid input");
        }
        if (negativeNumbers.length() > 0) {
            throw new IllegalArgumentException("\nERROR: negatives not allowed " + negativeNumbers.toString().replace("[", "").replace("]", ""));
        }
        return sum;
    }
}