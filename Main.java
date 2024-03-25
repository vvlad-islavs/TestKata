
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    protected static int index;
    protected static int oper;
    protected static int arabian;
    protected static int arabian2;
    protected static boolean isRoman;


    public static void main(String[] args) {
        HashMap<Integer, String> maparab = new HashMap<>();
        maparab.put(1, "I");
        maparab.put(2, "II");
        maparab.put(3, "III");
        maparab.put(4, "IV");
        maparab.put(5, "V");
        maparab.put(6, "VI");
        maparab.put(7, "VII");
        maparab.put(8, "VIII");
        maparab.put(9, "IX");
        maparab.put(10, "X");
        maparab.put(50, "L");
        maparab.put(100, "C");
        maparab.put(500, "D");
        maparab.put(1000, "M");


        System.out.println("Напишите выржаение:");
        String str;
        str = getStroke(maparab);
        if (!(str == null)) {
            System.out.println(str);
        }
    }

    private static String getStroke(HashMap maparab) {
        String stroke = scanner.nextLine().trim();
        index = stroke.indexOf("+");
        if (stroke.contains("+") ||
                stroke.contains("*") ||
                stroke.contains("-") ||
                stroke.contains("/")) {
            getOperation(stroke, maparab);
            return null;
        } else
            return "Неверное выражение!";
    }

    private static void getOperation(String stroke, HashMap mapRoman) {
        if (stroke.contains("+")) {
            oper = '+';
        }
        if (stroke.contains("*")) {

            oper = '*';
        }
        if (stroke.contains("-")) {
            oper = '-';
        }
        if (stroke.contains("/")) {
            oper = '/';
        }
        index = stroke.indexOf(oper);
        isRoman = isFirstRoman(mapRoman, stroke) & isSecondRoman(mapRoman, stroke);

        if (isRoman) {
//            System.out.println("роман");
            operation(arabian, arabian2);

        } else if (isSecondRoman(mapRoman, stroke) || isFirstRoman(mapRoman, stroke)) {
            System.out.println("Неверное выражение!");
        } else {
            int first = Integer.parseInt(stroke.substring(0, index).trim());
            int second = Integer.parseInt(stroke.substring(index + 1, stroke.length()).trim());
            operation(first, second);

        }
    }


    private static boolean isFirstRoman(HashMap map, String stroke) {
        for (int i = 0; i < 11; i++) {
            if (stroke.substring(0, index).trim().equals(map.get(i))) {
                arabian = i;
                return true;
            }
        }
        return false;
    }

    private static boolean isSecondRoman(HashMap map, String stroke) {

        for (int i = 0; i < 11; i++) {
            if (stroke.substring(index + 1, stroke.length()).trim().equals(map.get(i))) {
                arabian2 = i;
//                System.out.println(arabian2);
                return true;
            }
        }
//        System.out.println(arabian2);
        return false;

    }

    private static void operation(int first, int second) {
        int res = 0;
        if (oper == '+') {
            res = first + second;
//            System.out.println("Сложение");
        }
        if (oper == '-') {
            res = first - second;
//            System.out.println("Вычетание");
        }
        if (oper == '*') {
            res = first * second;
//            System.out.println("Умножение");
        }
        if (oper == '/') {
            res = first / second;
//            System.out.println("Деление");
        }
        if (isRoman & res > 0) {
            converter(res);

        } else if (!isRoman){
            System.out.println(res);
        } else System.out.println("Невероное выражение!");
    }
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    public static String toRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder roman = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                roman.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return roman.toString();
    }
    public static void converter(int i) {
        String res = toRoman(i);
        System.out.println(res);
    }
}



