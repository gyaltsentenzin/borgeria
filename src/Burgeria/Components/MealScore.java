package Burgeria.Components;

/**
     * MealScore serves as a class to calculate and store the score of a specific customer order based on the time it is submitted.
     * Created by Jack Keller, Tenzin Gyaltsen, Emydius Montes and Arthur Motoyama.
     */
public class MealScore {
    private CustomerOrder submittedMeal;
    private double score;
    private double time;
    
    /**
     * Constructor for MealScore that sets submittedMeal and time to given parameters.
     * @param submittedMeal
     * @param time
     */
    public MealScore(CustomerOrder submittedMeal, double time){
        this.submittedMeal = submittedMeal;
        this.time = time;
    }

    private double CalculateScore(double time){
        score = time;
        return score;

    }
    
}
