package org.example;

public enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor){
        this.conversionFactor=conversionFactor;
    }
//    @Override
    public double getConversionFactor(){
        return conversionFactor;
    }
    //Convert value in this unit → base unit (kilogram)
    @Override
    public double convertToBaseUnit(double value){
        return value*conversionFactor;
    }

    // Convert value from base unit (Kilogram) → this unit
    @Override
    public double convertFromBaseUnit(double baseValue){
        return baseValue/conversionFactor;
    }

    @Override
    public String getUnitName(){
        return name();
    }

}
