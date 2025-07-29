package lesson_3_number_1;

class Animal {
    private final String name;
    private static int count = 0;

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public void run(int distance) {
        System.out.println(name + " пробежал " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м.");
    }

    public String getName() {
        return name;
    }

    public static int getCount() {
        return count;
    }
}