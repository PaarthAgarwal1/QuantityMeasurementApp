package org.example;

public enum VolumeUnit implements IMeasurable{
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor){
        this.conversionFactor=conversionFactor;
    }

    @Override
    public double getConversionFactor(){
        return conversionFactor;
    }
    // Convert value in this unit → base unit (liter)
    @Override
    public double convertToBaseUnit(double value){
        return value * conversionFactor;
    }
    // Convert value from base unit (liter) → this unit
    @Override
    public double convertFromBaseUnit(double baseValue){
        return baseValue/conversionFactor;
    }

    @Override
    public String getUnitName(){
        return name();
    }
}
