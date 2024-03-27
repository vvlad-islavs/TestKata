
import java.io.IOError;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    protected static int index;
    protected static int oper;
    protected static int roman;
    protected static int roman2;
    protected static boolean isRoman;


    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> mapRoman = new HashMap<>();
        mapRoman.put(1, "I");
        mapRoman.put(2, "II");
        mapRoman.put(3, "III");
        mapRoman.put(4, "IV");
        mapRoman.put(5, "V");
        mapRoman.put(6, "VI");
        mapRoman.put(7, "VII");
        mapRoman.put(8, "VIII");
        mapRoman.put(9, "IX");
        mapRoman.put(10, "X");
//        mapRoman.put(50, "L");
//        mapRoman.put(100, "C");
//        mapRoman.put(500, "D");
//        mapRoman.put(1000, "M");


        System.out.println("Напишите выражение:");
        String str;
        str = getStroke(mapRoman);
        if (!(str == null)) {
            System.out.println(str);
        }
    }

    private static String getStroke (HashMap mapRoman) throws IOException {
        String stroke = scanner.nextLine().trim();
//        index = stroke.indexOf("+");
        if (stroke.contains("+") ||
                stroke.contains("*") ||
                stroke.contains("-") ||
                stroke.contains("/")) {
            getOperation(stroke, mapRoman);
            return null;
        } else {
//            return "Неверное выражение!";
            throw new IOException();
        }
    }


    private static void getOperation(String stroke, HashMap mapRoman) {
        if (stroke.contains("+")) {
            oper = '+';
//            index = stroke.indexOf('+');
        }
        if (stroke.contains("*")) {
            oper = '*';
//            index = stroke.indexOf('*');
        }
        if (stroke.contains("-")) {
            oper = '-';
//            index = stroke.indexOf('-');
        }
        if (stroke.contains("/")) {
            oper = '/';
//            index = stroke.indexOf('/');
        }
        index = stroke.indexOf(oper);
        isRoman = isFirstRoman(mapRoman, stroke) & isSecondRoman(mapRoman, stroke);

        if (isRoman) {
//            System.out.println("роман");
            operation(roman, roman2);

        } else if (isSecondRoman(mapRoman, stroke) || isFirstRoman(mapRoman, stroke)) {
//            System.out.println("Неверное выражение!");
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            int first = Integer.parseInt(stroke.substring(0, index).trim());
            int second = Integer.parseInt(stroke.substring(index + 1, stroke.length()).trim());
            if (first<=10&second<=10){
                operation(first, second);
            }
            else {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                System.out.println("Число больше 10!");
            }

        }
    }


    private static boolean isFirstRoman(HashMap map, String stroke) {
        for (int i = 0; i < 11; i++) {
            if (stroke.substring(0, index).trim().equals(map.get(i))) {
                roman = i;
                return true;
            }
        }
        return false;
    }

    private static boolean isSecondRoman(HashMap map, String stroke) {

        for (int i = 0; i < 11; i++) {
            if (stroke.substring(index + 1, stroke.length()).trim().equals(map.get(i))) {
                roman2 = i;
//                System.out.println(roman2);
                return true;
            }
        }
//        System.out.println(roman2);
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
            if(second != 0)
                res = first / second;
            else
                try {
                    throw new IOException();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//            System.out.println("Деление");
        }
        if (isRoman) {
            if(res>0)
                converter(res);
            else {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if(!isRoman)
            System.out.println(res);

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



