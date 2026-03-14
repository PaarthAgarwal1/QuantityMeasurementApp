package org.example;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }
    public static void demonstrateLengthComparison(double value1,Length.LengthUnit unit1,double value2,Length.LengthUnit unit2){
        Length length1=new Length(value1,unit1);
        Length length2=new Length(value2,unit2);
        System.out.println(unit1+" and "+unit2+" comparison: "+length1.equals(length2));
    }
    public static double convert(double value,Length.LengthUnit source,Length.LengthUnit target){
        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if(source==null||target==null){
            throw new IllegalArgumentException("Units cannot be null");
        }
        double baseValue=value*source.getConversionFactor();
        return baseValue/target.getConversionFactor();
    }
    public static void demonstrateLengthConversion(double value,Length.LengthUnit from,Length.LengthUnit to){
        double result=convert(value,from,to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }
    public static void demonstrateAddition(double value1,Length.LengthUnit unit1,double value2,Length.LengthUnit unit2){
        Length length1=new Length(value1,unit1);
        Length length2=new Length(value2,unit2);

        Length result=length1.add(length2);
        System.out.println("add(" + length1 + ", " + length2 + ") = " + result);
    }
    public static void main(String[] args) {
        demonstrateAddition(1.0, Length.LengthUnit.FEET, 2.0, Length.LengthUnit.FEET);

        demonstrateAddition(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES);

        demonstrateAddition(12.0, Length.LengthUnit.INCHES, 1.0, Length.LengthUnit.FEET);

        demonstrateAddition(1.0, Length.LengthUnit.YARDS, 3.0, Length.LengthUnit.FEET);

        demonstrateAddition(36.0, Length.LengthUnit.INCHES, 1.0, Length.LengthUnit.YARDS);

        demonstrateAddition(2.54, Length.LengthUnit.CENTIMETERS, 1.0, Length.LengthUnit.INCHES);

        demonstrateAddition(5.0, Length.LengthUnit.FEET, 0.0, Length.LengthUnit.INCHES);

        demonstrateAddition(5.0, Length.LengthUnit.FEET, -2.0, Length.LengthUnit.FEET);
    }
}
