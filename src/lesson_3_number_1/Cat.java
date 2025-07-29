package lesson_3_number_1;

class Cat extends Animal {
    private static final int MAX_RUN = 200;
    private boolean full;
    private static int count = 0;

    public Cat(String name) {
        super(name);
        full = false;
        count++;
    }

    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN) {
            super.run(distance);
        } else {
            System.out.println(getName() + " не может пробежать больше " + MAX_RUN + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(getName() + " не умеет плавать!");
    }

    public void eat(Bowl bowl) {
        int neededFood = 10; // Кот ест 10 единиц за раз
        if (bowl.getFoodAmount() >= neededFood) {
            bowl.decreaseFood(neededFood);
            full = true;
            System.out.println(getName() + " поел из миски");
        } else {
            System.out.println(getName() + " не стал есть: в миске мало еды");
        }
    }

    public boolean isFull() {
        return full;
    }

    public static int getCount() {
        return count;
    }
}