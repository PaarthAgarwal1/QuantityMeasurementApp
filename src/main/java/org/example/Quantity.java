package org.example;

public class Quantity<U extends IMeasurable> {
    private enum ArithmeticOperation {
        ADD {
            @Override
            double compute(double a, double b) {
                return a + b;
            }
        },
        SUBTRACT {
            @Override
            double compute(double a, double b) {
                return a - b;
            }
        },
        DIVIDE {
            @Override
            double compute(double a, double b) {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            }
        };

        abstract double compute(double a, double b);
    }
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

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible measurement categories");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Values must be finite");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (targetUnit != null && !this.unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Target unit category mismatch");
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        this.unit.validateOperationSupport(operation.name());
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
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

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, targetUnit);
    }
    //Subtraction
    public Quantity<U> subtract(Quantity<U> other){
        return subtract(other,this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, targetUnit);
    }

    //Divison
    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
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
