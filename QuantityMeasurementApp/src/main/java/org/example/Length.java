package org.example;

public class Length {

    private double value;
    private LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Constructor
    public Length(double value, LengthUnit unit) {
        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if(unit==null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }
    public double getValue(){
        return value;
    }
    public LengthUnit getUnit(){
        return unit;
    }
    // Convert to base unit (inches)
    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    //Convert to another unit
    public double convertTo(LengthUnit targetUnit){
        if(targetUnit==null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue=convertToBaseUnit();
        return baseValue/targetUnit.getConversionFactor();
    }
    //Addition
    public Length add(Length other){
        if(other==null){
            throw new IllegalArgumentException("Second operand cannot be null");
        }
        double thisBase=this.convertToBaseUnit();
        double otherBase=other.convertToBaseUnit();

        double sumBase=thisBase+otherBase;
        double resultValue=sumBase/this.unit.getConversionFactor();
        return new Length(resultValue,this.unit);
    }
    // Compare two Length objects
    public boolean compare(Length thatLength) {
        if (thatLength == null)
            return false;
        double epsilon = 0.0001;
        return Math.abs(this.convertToBaseUnit() - thatLength.convertToBaseUnit()) < epsilon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;

        Length that = (Length) o;
        return compare(that);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    // Main for quick testing
    public static void main(String[] args) {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2)); // true

        Length length3 = new Length(1.0, LengthUnit.YARDS);
        Length length4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length3.equals(length4)); // true

        Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length length6 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length5.equals(length6)); // true


    }
}