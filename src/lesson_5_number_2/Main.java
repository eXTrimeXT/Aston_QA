package lesson_5_number_2;

public class Main {
    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        phoneDirectory.add("Иванов", "123-456");
        phoneDirectory.add("Петров", "789-012");
        phoneDirectory.add("Иванов", "345-678");

        System.out.println("Телефоны Иванова: " + phoneDirectory.get("Иванов"));
        System.out.println("Телефоны Петрова: " + phoneDirectory.get("Петров"));
        System.out.println("Телефоны Сидорова: " + phoneDirectory.get("Сидоров"));
    }
}
