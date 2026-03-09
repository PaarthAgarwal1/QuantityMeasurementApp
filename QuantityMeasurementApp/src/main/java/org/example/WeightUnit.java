package org.example;

public enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toConversionFactor;

    WeightUnit(double toConversionFactor){
        this.toConversionFactor=toConversionFactor;
    }

    //Convert value in this unit → base unit (kilogram)
    public double convertToBaseUnit(double value){
        return value*toConversionFactor;
    }

    // Convert value from base unit (Kilogram) → this unit
    public double convertFromBaseUnit(double baseValue){
        return baseValue/toConversionFactor;
    }
}
