package TZJanosi.y2015.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Cook {
    private List<Ingredient> ingredients = new ArrayList<>();
    private int numberOfTeeSpoons = 100;
    private int maxScore = Integer.MIN_VALUE;

    public Cook(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    public int findMaxScore() {
        findRatio(new ArrayList<>());
        return maxScore;
    }

    private void findRatio(List<Integer> ratios) {
        int alreadyUsed = ratios.stream().mapToInt(x -> x.intValue()).sum();
        int countOfIngredients = ingredients.size();
        for (int i = 0; i <= numberOfTeeSpoons - alreadyUsed; i++) {
            int nextAlreadyUsed = alreadyUsed + i;
            List<Integer> nextRatios = new ArrayList<>(ratios);
            nextRatios.add(i);
            if (nextRatios.size() == countOfIngredients - 1) {
                nextRatios.add(numberOfTeeSpoons - nextAlreadyUsed);
                calculateScore(nextRatios);
            } else {
                findRatio(nextRatios);
            }
        }
    }

    private void calculateScore(List<Integer> amounts) {
        setAmount(amounts);
        buildScore();
    }

    private void buildScore() {
        int totalCapacity = Math.max(0, buildScoreForProperty(Ingredient::getTotalCapacity));
        int totalDurability = Math.max(0, buildScoreForProperty(Ingredient::getTotalDurability));
        int totalFlavor = Math.max(0, buildScoreForProperty(Ingredient::getTotalFlavor));
        int totalTexture = Math.max(0, buildScoreForProperty(Ingredient::getTotalTexture));

        int actualScore = totalCapacity * totalDurability * totalFlavor * totalTexture;
        maxScore = Math.max(maxScore, actualScore);
    }

    private int buildScoreForProperty(Function<Ingredient, Integer> function) {
        return ingredients.stream().mapToInt(i -> function.apply(i)).sum();
    }

    private void setAmount(List<Integer> amounts) {
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            ingredient.setAmount(amounts.get(i));
        }
    }

    private void processLine(String line) {
        String[] words = line.split(" ");
        Ingredient ingredient = createIngredient(words);
        ingredients.add(ingredient);
    }

    private Ingredient createIngredient(String[] words) {
        String name = trimLastCharacter(words[0]);
        int capacity = Integer.parseInt(trimLastCharacter(words[2]));
        int durability = Integer.parseInt(trimLastCharacter(words[4]));
        int flavor = Integer.parseInt(trimLastCharacter(words[6]));
        int texture = Integer.parseInt(trimLastCharacter(words[8]));
        int calories = Integer.parseInt(words[10]);
        Ingredient ingredient = new Ingredient(name, capacity, durability, flavor, texture, calories);
        return ingredient;
    }

    private String trimLastCharacter(String word) {
        return word.substring(0, word.length() - 1);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Cook{" +
                "ingredients=" + ingredients +
                '}';
    }
}
