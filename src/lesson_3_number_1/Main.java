package lesson_3_number_1;

public class Main {
    public static void main(String[] args) {
        // Создаем животных
        Cat cat1 = new Cat("Барсик");
        Dog dog1 = new Dog("Бобик");

        // Тестируем бег и плавание
        cat1.run(150);
        cat1.swim(10);
        dog1.run(400);
        dog1.swim(5);

        // Создаем миску и массив котов
        Bowl bowl = new Bowl(30); // Миска с 30 единицами еды
        Cat[] cats = {
                new Cat("Васька"),
                new Cat("Рыжик"),
                new Cat("Черныш")
        };

        // Кормим всех котов
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        // Проверяем сытость
        for (Cat cat : cats) {
            System.out.printf(cat.getName() + " сыт: " + cat.isFull() + "\n");
        }

        // Добавляем еду в миску
        bowl.addFood(50);
        System.out.println("В миске теперь: " + bowl.getFoodAmount() + " еды");

        // Выводим статистику животных
        System.out.println("\nВсего животных: " + Animal.getCount());
        System.out.println("Всего котов: " + Cat.getCount());
        System.out.println("Всего собак: " + Dog.getCount());
    }
}