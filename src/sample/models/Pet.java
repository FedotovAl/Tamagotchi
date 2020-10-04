package sample.models;

import java.io.Serializable;

public class Pet implements Serializable {
    private String name;
    private String skinEgg;
    private String skinHappy;
    private String skinSad;
    private int age;
    private int maxAge;
    private Food favoriteFood;
    private int hunger;
    private int happiness;
    private int cheerfulness;
    private int hungerIndex;
    private int happinessIndex;
    private int cheerfulnessIndex;
    private boolean isAlive;

    public void letsPlay(){
        hunger = hunger - 5 * hungerIndex;
        if (hunger < 0){
            hunger = 0;
        }

        happiness = happiness + 15 * happinessIndex;
        if (happiness > 100){
            happiness = 100;
        }

        cheerfulness = cheerfulness - 5 * cheerfulnessIndex;
        if (cheerfulness < 0){
            cheerfulness = 0;
        }
    }

    public void letsEat(Food food){
        int favoriteFoodIndex = 1;
        if (food == favoriteFood) {

            favoriteFoodIndex = 2;
        }
        hunger = hunger + 10 * hungerIndex * favoriteFoodIndex;
        if (hunger > 100){
            hunger = 100;
        }

        happiness = happiness + 5 * happinessIndex * favoriteFoodIndex;
        if (happiness > 100){
            happiness = 100;
        }
    }

    public void letsSleep(){
        hunger = hunger - 5 * hungerIndex;
        if (hunger < 0){
            hunger = 0;
        }

        happiness = happiness + 5 * happinessIndex;
        if (happiness > 100){
            happiness = 100;
        }

        cheerfulness = cheerfulness + 30 * cheerfulnessIndex;
        if (cheerfulness > 100){
            cheerfulness = 100;
        }
    }

    public Pet(String name,
               String skinEgg,
               String skinHappy,
               String skinSad,
               int maxAge,
               Food favoriteFood,
               int hungerIndex,
               int happinessIndex,
               int cheerfulnessIndex
    ) {
        this.name = name;
        this.skinEgg = skinEgg;
        this.skinHappy = skinHappy;
        this.skinSad = skinSad;
        this.age = -1;
        this.maxAge = maxAge;
        this.favoriteFood = favoriteFood;
        this.hunger = 50;
        this.happiness = 50;
        this.cheerfulness = 50;
        this.hungerIndex = hungerIndex;
        this.happinessIndex = happinessIndex;
        this.cheerfulnessIndex = cheerfulnessIndex;
        this.isAlive = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkinEgg() {
        return skinEgg;
    }

    public void setSkinEgg(String skinEgg) {
        this.skinEgg = skinEgg;
    }

    public String getSkinHappy() {
        return skinHappy;
    }

    public void setSkinHappy(String skinHappy) {
        this.skinHappy = skinHappy;
    }

    public String getSkinSad() {
        return skinSad;
    }

    public void setSkinSad(String skinSad) {
        this.skinSad = skinSad;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Food getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(Food favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getCheerfulness() {
        return cheerfulness;
    }

    public void setCheerfulness(int cheerfulness) {
        this.cheerfulness = cheerfulness;
    }

    public int getHungerIndex() {
        return hungerIndex;
    }

    public void setHungerIndex(int hungerIndex) {
        this.hungerIndex = hungerIndex;
    }

    public int getHappinessIndex() {
        return happinessIndex;
    }

    public void setHappinessIndex(int happinessIndex) {
        this.happinessIndex = happinessIndex;
    }

    public int getCheerfulnessIndex() {
        return cheerfulnessIndex;
    }

    public void setCheerfulnessIndex(int cheerfulnessIndex) {
        this.cheerfulnessIndex = cheerfulnessIndex;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", skinEgg='" + skinEgg + '\'' +
                ", skinHappy='" + skinHappy + '\'' +
                ", skinSad='" + skinSad + '\'' +
                ", age=" + age +
                ", maxAge=" + maxAge +
                ", favoriteFood=" + favoriteFood +
                ", hunger=" + hunger +
                ", happiness=" + happiness +
                ", cheerfulness=" + cheerfulness +
                ", hungerIndex=" + hungerIndex +
                ", happinessIndex=" + happinessIndex +
                ", cheerfulnessIndex=" + cheerfulnessIndex +
                ", isAlive=" + isAlive +
                '}';
    }
}
