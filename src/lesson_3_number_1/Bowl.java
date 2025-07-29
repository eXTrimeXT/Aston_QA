package lesson_3_number_1;

class Bowl {
    private int foodAmount;

    public Bowl(int foodAmount) {
        this.foodAmount = Math.max(foodAmount, 0); // Не допускаем отрицательное значение
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void decreaseFood(int amount) {
        if (amount <= foodAmount) {
            foodAmount -= amount;
        }
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
        }
    }
}