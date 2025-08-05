package lesson_4;

// Пользовательское исключение для некорректных данных в массиве
public class MyArrayDataException extends Exception{
    public MyArrayDataException(String message) {
        super(message);
    }
}
