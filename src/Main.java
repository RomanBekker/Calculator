import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("������� ���� �� ��������� �������� � ����� ������� (�� 1 �� 10): a + b, a - b, a * b, a / b (�����_����_�����)");
        System.out.println("Input:");
        String input = reader.readLine();
        System.out.println("Output:\n" + calc(input));
    }

    public static String calc(String input) {
        //������������� �������� ������ � ������ �����:
        String[] strings = input.split(" ");
        if (strings.length > 3) {
            return "throws Exception //�.�. ������ �������������� �������� �� ������������� ������� - ��� �������� � ���� �������� (+, -, /, *)";
        } else if (strings.length < 3) {
            return "��� �������� � ���� �������� ������ ���� ��������� ����� ����� ��������� (�����_����_�����)";
        }

        //���� ��� ������� �����:
        int a = 0;
        int b = 0;
        int resultRim = 0;
        //���� ��� �������� �����:
        int c = 0;
        int d = 0;
        int resultArab = 0;

        //����������, � ����� ������� ��������� ��������� ��������� �����:
        //���� � �������, �� ��������� ���� a � b ��� ������� ����� ��������� ���������� (��� ������ ������ getArab).
        for (Rim x : Rim.values()) {
            if (x.toString().equals(strings[0])) {
                a = x.getArab();
            }
            if (x.toString().equals(strings[2])) {
                b = x.getArab();
            }
        }

        //���� �� �������� ����� �������, ������ ��������
        if ((a == 0 && b != 0) || (a != 0 && b == 0)) {
            return "throws Exception //�.�. ������������ ������������ ������ ������� ��������� ���� ������� ����� ��� ��������� �� 1 �� 10";
        }

        //���� � ��������, �� ��������� ���� d � c ��� �������� ����� (���������� ������ � ������ ������ �������).
        if (a == 0 && b == 0) {
            //���������� �� ���� ������ ����� �����
            try {
                c = Integer.parseInt(strings[0]);
                d = Integer.parseInt(strings[2]);
            } catch (NumberFormatException e) {
                return "���������� ������� ������ ����� �����";
            }
            //����������� �� ���� �������� ����� (�� 1 �� 10)
            if (c > 10 || c < 1 || d > 10 || d < 1) {
                return "���������� ������ ����� �� 1 �� 10!";
            }
        }

        //���������� �������������� ���������� � �������� �������:
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
                return "throws Exception //�.�. ������ �������������� �������� �� ������������� ������� - ��� �������� � ���� �������� (+, -, /, *)";
            }
        }

        //���������� �������������� ���������� � ��������� �������:
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
                return "throws Exception //�.�. ������ �������������� �������� �� ������������� ������� - ��� �������� � ���� �������� (+, -, /, *)";
            }
        }

        //������� ��� �������� ������������ �������� � ������� ������� ���������:
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII",
                "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII",
                "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII",
                "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
                "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII",
                "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII",
                "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        //����������� ��������:
        String result;
        if (a == 0 && b == 0) {
            result = resultArab + "";
        } else if (resultRim > 0) {
            result = roman[resultRim];
        } else {
            return "throws Exception //�.�. � ������� ������� ��� ������������� ����� � ����";
        }
        return result;
    }
}
