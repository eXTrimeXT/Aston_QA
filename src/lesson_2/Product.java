package lesson_2;

public class Product {
    String name, date, producer, country;
    double price;
    boolean isBooking;

    public Product(String name, String date, String producer, String country, double price, boolean isBooking) {
        this.name = name;
        this.date = date;
        this.producer = producer;
        this.country = country;
        this.price = price;
        this.isBooking = isBooking;
    }

    public void outputProduct(){
        System.out.println("\nНазвание: " + name);
        System.out.println("Дата: " + date);
        System.out.println("Производитель: " + producer);
        System.out.println("Страна происхождения: " + country);
        System.out.println("Цена: " + price);
        System.out.println("Состояние бронирования: " + isBooking);
    }

    public static void main(String[] args) {
        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("Xiaomi A4", "02.03.2025", "Xiaomi Corp.", "China", 4599, true);
        productsArray[2] = new Product("OIS 13", "03.03.2025", "Apple", "USA", 5999, false);
        productsArray[3] = new Product("BMW E39", "04.03.1995", "BMW Corp", "Germany", 21999, true);
        productsArray[4] = new Product("Audi A8", "05.03.2021", "VAG Corp", "Germany", 34999, false);

        for (Product prod: productsArray) {
            prod.outputProduct();
        }
    }
}