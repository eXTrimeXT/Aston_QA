package lesson_3_number_2;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(5, "красный", "черный"),
                new Rectangle(4, 6, "синий", "белый"),
                new Triangle(3, 4, 5, "зеленый", "желтый")
        };

        for (Shape shape : shapes) {
            shape.printInfo();
        }
    }
}