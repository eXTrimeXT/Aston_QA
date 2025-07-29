package lesson_3_number_2;

public interface Shape {
    double calculateArea();
    String getFillColor();
    String getBorderColor();

    default double calculatePerimeter() {
        return 0;
    }

    default void printInfo() {
        System.out.printf("[Периметр: %.2f, Площадь: %.2f, Цвет фона: %s, Цвет границ: %s]%n",
                calculatePerimeter(), calculateArea(), getFillColor(), getBorderColor());
    }
}
