package org.example;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit){
        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if(unit == null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value=value;
        this.unit=unit;
    }

    public double getValue(){
        return value;
    }

    public U getUnit(){
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue=unit.convertToBaseUnit(value);

        double convertedValue=targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(convertedValue),targetUnit);
    }

    public Quantity<U> add(Quantity<U> other){
        if(other==null){
            throw new IllegalArgumentException("Second operand cannot be null");
        }
        double sumBase=unit.convertToBaseUnit(value)+ other.unit.convertToBaseUnit(other.value);
        double resultValue=unit.convertFromBaseUnit(sumBase);
        return new Quantity<>(round(resultValue),unit);
    }
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase =
                unit.convertToBaseUnit(value)
                        + other.unit.convertToBaseUnit(other.value);

        double resultValue = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(round(resultValue), targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase-otherBase)<0.000001;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
