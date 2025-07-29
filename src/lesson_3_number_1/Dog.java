package lesson_3_number_1;

class Dog extends Animal {
    private static final int MAX_RUN = 500;
    private static final int MAX_SWIM = 10;
    private static int count = 0;

    public Dog(String name) {
        super(name);
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
        if (distance <= MAX_SWIM) {
            super.swim(distance);
        } else {
            System.out.println(getName() + " не может проплыть больше " + MAX_SWIM + " м.");
        }
    }

    public static int getCount() {
        return count;
    }
}