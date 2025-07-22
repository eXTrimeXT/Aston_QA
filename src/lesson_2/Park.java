package lesson_2;

public class Park {
    public static class Attraction {
        String name;
        String workingHours;
        double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void outputInfo() {
            System.out.println("Карусель " + name + " работает: " + workingHours + ", цена: " + price + " рублей");
        }
    }

    public static void main(String[] args) {
        Attraction carousel = new Attraction("бумеранг", "10:00-20:00", 150.50);
        carousel.outputInfo();
    }
}