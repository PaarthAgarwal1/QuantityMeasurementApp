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

    public static void main(String[] args) {
        demonstrateLengthComparison(1.0, Length.LengthUnit.FEET,12.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS,36.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(100.0, Length.LengthUnit.CENTIMETERS,39.3701, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(3.0, Length.LengthUnit.FEET,1.0, Length.LengthUnit.YARDS);
        demonstrateLengthComparison(30.48, Length.LengthUnit.CENTIMETERS,1.0, Length.LengthUnit.FEET);
    }
}
