package lesson_4;

// Пользовательское исключение для неверного размера массива
public class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}
