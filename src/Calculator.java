package src;
import java.util.*;
public class Calculator {
    static String regex = "^[0-9]+$";
    static String operandOne;
    static String operator;
    static String operandTwo;

    static String[] romanOneToTen = {null, "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X"};
    static List<String> romanOneToTenList = Arrays.asList(romanOneToTen);

    public static void arabCalc() throws Exception {
        char op = operator.charAt(0);
        int a = Integer.parseInt(operandOne);
        int b = Integer.parseInt(operandTwo);
        if (a < 11 && a > 0 && b < 11 && b > 0) {
            int result;
            switch (op) {
                case '+' -> result = a + b;
                case '-' -> result = a - b;
                case '*' -> result = a * b;
                case '/' -> result = a / b;
                default -> throw new Exception("неизвестный оператор!");
            }
            System.out.println(result);
        } else {
            throw new Exception("допустимое значение операндов: от 1 до 10");
        }
    }
    public static int romanCalc() throws Exception {
        char op = operator.charAt(0);
        int a = romanOneToTenList.indexOf(operandOne);
        int b = romanOneToTenList.indexOf(operandTwo);
        int result;

        if (a < 11 && a > 0 && b < 11 && b > 0) {
            result = switch (op) {
                case '+' -> a + b;
                case '-' -> a - b;
                case '*' -> a * b;
                case '/' -> a / b;
                default -> throw new Exception("неизвестный оператор!");
            };
            return result;
        } else {
            throw new Exception("допустимое значение операндов: от I до X");
        }
    }
    public static void arabToRoman() throws Exception {
        int input = romanCalc();
       if (input < 1) { throw new Exception("в римской системе нет ноля и отрицательных чисел");
               }
       String result = "I".repeat(input)
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C");
        System.out.println(result);
    }
    public static void main( String[] args ) throws Exception  {
        Scanner scanner = new Scanner(System.in);
        String task = scanner.nextLine();
        scanner.close();
        String[] parts = task.split(" ");
        if(parts.length < 3) {
            throw new Exception("строка не является математической операцией");
        } if(parts.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        operandOne = parts[0];
        operator = parts[1];
        operandTwo = parts[2];
        if((romanOneToTenList.contains(operandOne) && operandTwo.matches(regex)) ||
                (romanOneToTenList.contains(operandTwo) && operandOne.matches(regex))){
            throw new Exception("используются одновременно разные системы исчисления");
        }
        if (operandOne.matches(regex) && operandTwo.matches(regex) ){
            Calculator.arabCalc();
        } else {
            Calculator.arabToRoman();
        }
    }
}


