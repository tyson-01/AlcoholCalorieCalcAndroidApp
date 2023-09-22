package com.AlcoholCalorieCalc.model;

public class Beer extends UnmixedDrink {
    private final int vol;

    public Beer(double abv, int vol, int sweetness) {
        this.abv = abv;
        this.vol = vol;
        //this residual sugar calculation is a linear approximation of a polynomial function I made
        //based off of a 0 to 10 perceived sweetness measure and data found from various breweries that
        //display carbohydrate information of their beers. it is meh, but close enough
        this.resSugar = 2.4 * sweetness + 3.6;
        this.cal = calculateCals();
    }

    private double calculateCals() {
        double kCalsFromEth = 5.4441 * vol * abv / 100;
        double kCalsFromResSugar = 4 * resSugar;
        return kCalsFromEth + kCalsFromResSugar;
    }

    public double getCals() {
        return this.cal;
    }
}