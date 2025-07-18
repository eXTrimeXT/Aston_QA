import java.util.Arrays;

public class Lesson_1 {

    // 1. Метод printThreeWords()
    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }

    // 2. Метод checkSumSign()
    public static void checkSumSign(int a, int b) {
        if (a+b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Метод printColor()
    public static void printColor(int value) {
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Метод compareNumbers()
    public static void compareNumbers(int a, int b) {
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Проверка суммы в диапазоне [10, 20]
    public static boolean isSumInRange(int num1, int num2) {
        int sum = num1 + num2;
        return sum >= 10 && sum <= 20;
    }

    // 6. Проверка числа на положительность (ноль считается положительным)
    public static void printNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    // 7. Проверка, является ли число отрицательным
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8. Печать строки N раз
    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    // 9. Проверка года на високосный
    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }

    // 10. Инвертирование массива 0 и 1
    public static void invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 0) ? 1 : 0;
        }
    }

    // 11. Заполнение массива числами от 1 до 100
    public static int[] fillArray1To100() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    // 12. Умножение чисел меньше 6 на 2
    public static void multiplyLessThan6(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }

    // 13. Заполнение диагоналей единицами в квадратном массиве
    public static void fillDiagonals(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 1;           // Главная диагональ
            matrix[i][n - 1 - i] = 1;    // Побочная диагональ
        }
    }

    // 14. Создание массива с одинаковыми значениями
    public static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

    // Демонстрация работы методов
    public static void main(String[] args) {
        System.out.println("### 1 ###");
        printThreeWords();
        System.out.println("### 2 ###");
        checkSumSign(5, -3);
        System.out.println("### 3 ###");
        printColor(54);
        System.out.println("### 4 ###");
        compareNumbers(10, 20);
        System.out.println("### 5 ###");
        System.out.println(isSumInRange(5, 7)); // true (12 в диапазоне)
        System.out.println("### 6 ###");
        printNumberSign(-5); // "Число отрицательное"
        System.out.println("### 7 ###");
        System.out.println(isNegative(0)); // false
        System.out.println("### 8 ###");
        printStringMultipleTimes("Hello", 3);
        System.out.println("### 9 ###");
        System.out.println(isLeapYear(2024)); // true
        System.out.println("### 10 ###");
        int[] binaryArray = {1, 1, 0, 0, 1};
        invertArray(binaryArray);
        System.out.println(Arrays.toString(binaryArray)); // [0, 0, 1, 1, 0]
        System.out.println("### 11 ###");
        int[] arr = fillArray1To100();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n### 12 ###");
        int[] numbersArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyLessThan6(numbersArray);
        System.out.println(Arrays.toString(numbersArray)); // [2, 10, 6, 4, 11, 8, 10, 4, 8, 8, 9, 2]
        System.out.println("### 13 ###");
        int[][] matrix = new int[5][5];
        fillDiagonals(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("### 14 ###");
        int[] filledArray = createArray(5, 42);
        System.out.println(Arrays.toString(filledArray)); // [42, 42, 42, 42, 42]
    }
}