package src;
import java.util.*;
public class Calculator {
    static String regex = "^[0-9]+$";
    static String part1;
    static String part2;
    static String part3;

    static String[] romanOneToTen = {null, "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X"};
    static List<String> romanOneToTenList = Arrays.asList(romanOneToTen);

    public static void arabCalc() throws Exception {
        char op = part2.charAt(0);
        int a = Integer.parseInt(part1);
        int b = Integer.parseInt(part3);
        if (a <= 10 && a > 0 && b <= 10 && b > 0) {
            int result;
            switch (op) {
                case '+' -> result = a + b;
                case '-' -> result = a - b;
                case '*' -> result = a * b;
                case '/' -> result = a / b;
                default -> throw new IllegalArgumentException("неизвестный оператор!");
            }
            System.out.println(result);
        } else {
            throw new Exception("допустимое значение операндов: от 1 до 10");
        }
    }
    public static int romanCalc() throws Exception {
        char op = part2.charAt(0);
        int a = romanOneToTenList.indexOf(part1);
        int b = romanOneToTenList.indexOf(part3);
        int result;

        if (a < 11 && a > 0 && b < 11 && b > 0) {
            result = switch (op) {
                case '+' -> a + b;
                case '-' -> a - b;
                case '*' -> a * b;
                case '/' -> a / b;
                default -> throw new IllegalArgumentException("неизвестный оператор!");
            };
            return result;
        } else {
            throw new Exception("допустимое значение операндов: от I до X");
        }
    }
    public static String arabToRoman() throws Exception {
        int input = romanCalc();
       if (input < 1) { throw new Exception("в римской системе нет ноля и отрицательных чисел");
               }
        return "I".repeat(input)
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C");
    }
    public static void main( String[] args ) throws Exception  {
        Scanner sc1 = new Scanner(System.in);
        String task1 = sc1.nextLine();
        String[] parts = task1.split(" ");
        sc1.close();
        if(parts.length < 3) {
            throw new Exception("строка не является математической операцией");
        } if(parts.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        part1 = parts[0];
        part2 = parts[1];
        part3 = parts[2];
        if((romanOneToTenList.contains(part1) && part3.matches(regex)) ||
                (romanOneToTenList.contains(part3) && part1.matches(regex))){
            throw new Exception("используются одновременно разные системы исчисления");
        }
        if (part1.matches(regex) && part3.matches(regex) ){
            Calculator.arabCalc();
        } else {
            Calculator.romanCalc();
            System.out.println(arabToRoman());
        }
    }
}


