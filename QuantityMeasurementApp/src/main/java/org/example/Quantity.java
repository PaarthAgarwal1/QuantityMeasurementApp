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
        if(!unit.getClass().equals(targetUnit.getClass())){
            throw new IllegalArgumentException("Incompatible unit categories");
        }
        double baseValue=unit.convertToBaseUnit(value);
        double convertedValue=targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(convertedValue,targetUnit);
    }
    //Validation
    private void validate(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible unit categories");
    }
    public Quantity<U> add(Quantity<U> other){
        return add(other,this.unit);
    }
    //Addition
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validate(other,targetUnit);

        double sumBase = unit.convertToBaseUnit(value) + other.unit.convertToBaseUnit(other.value);

        double resultValue = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(resultValue, targetUnit);
    }
    //Subtraction
    public Quantity<U> subtract(Quantity<U> other){
        return subtract(other,this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other,U targetUnit){
        validate(other,targetUnit);
        double resultBase = unit.convertToBaseUnit(value) - other.unit.convertToBaseUnit(other.value);
        double resultValue=targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(resultValue,targetUnit);
    }

    //Divison
    public double divide(Quantity<U> other){
        if(other==null){
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if(!unit.getClass().equals(other.unit.getClass())){
            throw new IllegalArgumentException("Incompatible unit categories");
        }

        double base1=unit.convertToBaseUnit(value);
        double base2=other.unit.convertToBaseUnit(other.value);

        if(base2==0){
            throw new ArithmeticException("Division by zero");
        }
        return base1/base2;
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
