package org.example;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }
    public static void demonstrateLengthComparison(double value1,LengthUnit unit1,double value2,LengthUnit unit2){
        Length length1=new Length(value1,unit1);
        Length length2=new Length(value2,unit2);
        System.out.println(unit1+" and "+unit2+" comparison: "+length1.equals(length2));
    }
    public static double convert(double value,LengthUnit source,LengthUnit target){
        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if(source==null||target==null){
            throw new IllegalArgumentException("Units cannot be null");
        }
        double baseValue=value*source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(baseValue);
    }
    public static void demonstrateLengthConversion(double value,LengthUnit from,LengthUnit to){
        double result=convert(value,from,to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }
    public static void demonstrateLengthAddition(double value1,LengthUnit unit1,double value2,LengthUnit unit2){
        Length length1=new Length(value1,unit1);
        Length length2=new Length(value2,unit2);

        Length result=length1.add(length2);
        System.out.println("add(" + length1 + ", " + length2 + ") = " + result);
    }
    public static void demonstrateLengthAddition(double value1,LengthUnit unit1,double value2,LengthUnit unit2,LengthUnit targetUnit){
        Length length1=new Length(value1,unit1);
        Length length2=new Length(value2,unit2);

        Length result=length1.add(length2,targetUnit);
        System.out.println("add(" + length1 + ", " + length2 + ", " + targetUnit + ") = " + result);
    }
    public static void main(String[] args) {
        demonstrateLengthAddition(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.FEET);

        demonstrateLengthAddition(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.INCHES);

        demonstrateLengthAddition(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.YARDS);

        demonstrateLengthAddition(1.0, LengthUnit.YARDS, 3.0, LengthUnit.FEET, LengthUnit.YARDS);

        demonstrateLengthAddition(36.0, LengthUnit.INCHES, 1.0, LengthUnit.YARDS, LengthUnit.FEET);

        demonstrateLengthAddition(2.54, LengthUnit.CENTIMETERS, 1.0, LengthUnit.INCHES, LengthUnit.CENTIMETERS);

        demonstrateLengthAddition(5.0, LengthUnit.FEET, 0.0, LengthUnit.INCHES, LengthUnit.YARDS);

        demonstrateLengthAddition(5.0, LengthUnit.FEET, -2.0, LengthUnit.FEET, LengthUnit.INCHES);
    }
}
