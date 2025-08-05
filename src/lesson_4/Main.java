package lesson_4;

public class Main {
    public static int sumMatrix(String[][] matrix) throws MyArraySizeException, MyArrayDataException {
        if (matrix.length != 4) {
            throw new MyArraySizeException("Неверное количество строк. Ожидается 4, получено " + matrix.length);
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != 4) {
                throw new MyArraySizeException(
                        String.format("Неверное количество столбцов в строке %d. Ожидается 4, получено %d", i, matrix[i].length)
                );
            }
        }

        // Суммирование элементов
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {
                    sum += Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Некорректные данные в ячейке [%d][%d]: '%s'", i, j, matrix[i][j]));
                }
            }
        }
        return sum;
    }

    // Метод для генерации ArrayIndexOutOfBoundsException
    public static void generateArrayIndexOutOfBounds() {
        int[] arr = new int[5];
        int value = arr[10];
    }

    public static void main(String[] args) {
        String[][] correctMatrix = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] wrongSizeMatrix = {
                {"1", "2", "3"},
                {"5", "6", "7"},
                {"9", "10", "11"}
        };

        String[][] wrongDataMatrix = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "XYZ"}
        };

        try {
            System.out.println("Сумма корректной матрицы: " + sumMatrix(correctMatrix));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма матрицы с неправильным размером: " + sumMatrix(wrongSizeMatrix));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма матрицы с некорректными данными: " + sumMatrix(wrongDataMatrix));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        // Генерация и обработка ArrayIndexOutOfBoundsException
        try {
            generateArrayIndexOutOfBounds();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }
}