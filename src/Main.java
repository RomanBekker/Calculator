import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите одну из следующих операций с двумя числами (от 1 до 10): a + b, a - b, a * b, a / b (число_знак_число)");
        System.out.println("Input:");
        String input = reader.readLine();
        System.out.println("Output:\n" + calc(input));
    }

    public static String calc(String input) {
        //Преобразовали входящую строку в массив строк:
        String[] strings = input.split(" ");
        if (strings.length > 3) {
            return "throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        } else if (strings.length < 3) {
            return "Два операнда и один оператор должны быть разделены между собой ПРОБЕЛАМИ (число_знак_число)";
        }

        //Поля для римских чисел:
        int a = 0;
        int b = 0;
        int resultRim = 0;
        //Поля для арабских чисел:
        int c = 0;
        int d = 0;
        int resultArab = 0;

        //Определяем, к какой системе счисления относятся введенные числа:
        //Если к римской, то заполняем поля a и b для римских чисел арабскими значениями (при помощи метода getArab).
        for (Rim x : Rim.values()) {
            if (x.toString().equals(strings[0])) {
                a = x.getArab();
            }
            if (x.toString().equals(strings[2])) {
                b = x.getArab();
            }
        }

        //Одно из вводимых чисел римское, другое арабское
        if ((a == 0 && b != 0) || (a != 0 && b == 0)) {
            return "throws Exception //т.к. используются одновременно разные системы счисления либо введены числа вне интервала от 1 до 10";
        }

        //Если к арабской, то заполняем поля d и c для арабских чисел (распарсить первую и третью строки массива).
        if (a == 0 && b == 0) {
            //Исключение на ввод только целых чисел
            try {
                c = Integer.parseInt(strings[0]);
                d = Integer.parseInt(strings[2]);
            } catch (NumberFormatException e) {
                return "Необходимо вводить только целые числа";
            }
            //Ограничение на ввод арабских чисел (от 1 до 10)
            if (c > 10 || c < 1 || d > 10 || d < 1) {
                return "Необходимо ввести числа от 1 до 10!";
            }
        }

        //Производим арифметические вычисления с римскими числами:
        if (a != 0 && b != 0) {

            if (strings[1].equals("+")) {
                resultRim = a + b;
            } else if (strings[1].equals("-")) {
                resultRim = a - b;
            } else if (strings[1].equals("/")) {
                resultRim = a / b;
            } else if (strings[1].equals("*")) {
                resultRim = a * b;
            } else {
                return "throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
            }
        }

        //Производим арифметические вычисления с арабскими числами:
        else if (a == 0 && b == 0) {
            if (strings[1].equals("+")) {
                resultArab = c + d;
            } else if (strings[1].equals("-")) {
                resultArab = c - d;
            } else if (strings[1].equals("/")) {
                resultArab = c / d;
            } else if (strings[1].equals("*")) {
                resultArab = c * d;
            } else {
                return "throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
            }
        }

        //Словарь для перевода вычисленного значения в римскую систему счисления:
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII",
                "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII",
                "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII",
                "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
                "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII",
                "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII",
                "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        //Вычисленное значение:
        String result;
        if (a == 0 && b == 0) {
            result = resultArab + "";
        } else if (resultRim > 0) {
            result = roman[resultRim];
        } else {
            return "throws Exception //т.к. в римской системе нет отрицательных чисел и нуля";
        }
        return result;
    }
}
