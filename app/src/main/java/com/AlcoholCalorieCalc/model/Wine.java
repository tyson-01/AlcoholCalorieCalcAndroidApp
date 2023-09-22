package com.AlcoholCalorieCalc.model;

public class Wine extends UnmixedDrink {
    public final int weight;

    public Wine(double abv, int grams, int sweetness) {
        this.abv = abv;
        this.weight = grams;
        //This is a approximation given avg carbs being from 1 to 18 grams 17/4 x - 13/4
        this.resSugar = ((double) sweetness * 17 - 13) / 4;
        this.cal = calculateCals();
    }

    public double calculateCals() {
        double kCalFromCarbs = resSugar * 4;
        //Citation: equation taken from joel andrew edited by James Henstridge (feb 8 2017)
        //post on https://alcohol.stackexchange.com/questions/1064/by-volume-by-weight-conversion-formula
        //I have not validated this ABV to ABW conversion
        double abvAsFraction = this.abv / 100;
        double abwAsFraction = 0.1893 * abvAsFraction * abvAsFraction + 0.7918 * abvAsFraction + 0.0002;
        double kCalFromEthanol = abwAsFraction * this.weight * 7;
        return kCalFromCarbs + kCalFromEthanol;
    }

    public double getCals() {
        return this.cal;
    }
}