package tzjanosi.y2015.day15;

import java.util.Objects;

public class Ingredient {
    private String name;
    private int capacity;
    private int durability;
    private int flavor;
    private int texture;
    private int calories;
    private int amount;

    public Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
        this.name = name;
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalCapacity() {
        return amount * capacity;
    }

    public int getTotalDurability() {
        return amount * durability;
    }

    public int getTotalFlavor() {
        return amount * flavor;
    }

    public int getTotalTexture() {
        return amount * texture;
    }

    public int getTotalCalories() {
        return amount * calories;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        return capacity == that.capacity && durability == that.durability && flavor == that.flavor && texture == that.texture && calories == that.calories && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, durability, flavor, texture, calories);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", durability=" + durability +
                ", flavor=" + flavor +
                ", texture=" + texture +
                ", calories=" + calories +
                ", amount=" + amount +
                "}\n";
    }
}
