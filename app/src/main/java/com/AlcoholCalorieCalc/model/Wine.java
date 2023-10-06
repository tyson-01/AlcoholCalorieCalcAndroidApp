package com.AlcoholCalorieCalc.model;

// This class represents a glass of wine
public class Wine extends UnmixedDrink {
    private final int weight;

    public Wine(double abv, int grams, double sweetness) {
        this.abv = abv;
        this.weight = grams;
        this.resSugar = calculateGramsOfSugar(sweetness);
        this.cal = calculateCals();
    }

    private double calculateGramsOfSugar(double sweetness) {
        // Human perceived sweetness is non-linear, we account for this here.
        // The scale for g of sugar per litre of wine used is from: https://winefolly.com/deep-dive/sugar-in-wine-chart/
        // This uses a piecemeal equation where integers [1-10] are used to lookup sugar(g)/L
        // If it is not an integer, we use linear interpolation to find it between these values
        // Sort of a quadratic equation if you squint your eyes
        Double sugarLookup[] = {1.0, 4.0, 10.0, 20.0, 35.0, 45.0, 70.0, 100.0, 135.0, 190.0};

        int lowerBound = (int) Math.floor(sweetness);
        int upperBound = (int) Math.ceil(sweetness);

        double lowerValue = sugarLookup[lowerBound - 1];
        double upperValue = sugarLookup[upperBound - 1];

        double gramsOfSugarPerLitre = lowerValue + (upperValue - lowerValue) * (sweetness - lowerBound);

        // *Ignoring here that 1g of wine isn't 1ml
        return (gramsOfSugarPerLitre / 1000) * this.weight;
    }

    private double calculateCals() {
        // Citation: equation taken from joel andrew edited by James Henstridge (feb 8 2017)
        // post on https://alcohol.stackexchange.com/questions/1064/by-volume-by-weight-conversion-formula
        // I have not validated this ABV to ABW conversion
        double abvAsFraction = this.abv / 100;
        double abwAsFraction = 0.1893 * abvAsFraction * abvAsFraction + 0.7918 * abvAsFraction + 0.0002;
        double kCalFromEthanol = abwAsFraction * this.weight * 7;

        double kCalFromCarbs = resSugar * 4;

        return kCalFromCarbs + kCalFromEthanol;
    }

    public double getCals() {return this.cal;}
}