public class Calculator {
    public static void main(String[] args){

        System.out.println(Calculator.add("1,2,3,4"));
        System.out.println(Calculator.add("//[(-_-')][%]\n1(-_-')2%3"));
        System.out.println(Calculator.add("//[abc][777][:(]\n1abc27773:(1"));

    }
    public static int add(String str) {
        String delimiter = ",\n";
        if (str.startsWith("//")) {
            delimiter += str.substring(2, str.indexOf("\n"));
            str = str.substring(str.indexOf("\n"));
        }
        return add(str, delimiter );
    }
    public static int add(String nums, String delimiter){
        char specialCharacter = '#';
        for(int i=0; i<delimiter.length(); i++){
            if((delimiter.charAt(i) == '[') || (delimiter.charAt(i) == ']')){
                continue;
            }else{
                nums = nums.replace(delimiter.charAt(i), specialCharacter);
            }
        }
        String[] arr = nums.split("#");
        StringBuilder negativeNumbers = new StringBuilder();
        int sum = 0;
        try {
            for (int i=0; i< arr.length; i++) {
                if (!arr[i].trim().isEmpty() && (Character.isDigit(arr[i].charAt(0)) || arr[i].charAt(0) == '-')) {
                    if (!Character.isDigit(nums.charAt(nums.length() - 1))) {
                        throw new IllegalArgumentException("ERROR: invalid input");
                    }
                    if (Integer.parseInt(arr[i].trim()) < 0) {
                        negativeNumbers.append(arr[i].trim()).append(" ");
                    } else if (Integer.parseInt(arr[i].trim()) < 1000) {
                        sum += Integer.parseInt(arr[i].trim());
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
