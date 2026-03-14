package org.example;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " equals " + q2 + " → " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U targetUnit) {
        System.out.println(q + " → " + q.convertTo(targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.println(q1 + " + " + q2 + " → " + q1.add(q2, targetUnit));
    }

    public static void main(String[] args) {

        System.out.println("----- Equality Comparisons -----");

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, VolumeUnit.LITRE)));

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));

        System.out.println(new Quantity<>(1.0, VolumeUnit.GALLON).equals(new Quantity<>(1.0, VolumeUnit.GALLON)));

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(0.264172, VolumeUnit.GALLON)));

        System.out.println(new Quantity<>(500.0, VolumeUnit.MILLILITRE).equals(new Quantity<>(0.5, VolumeUnit.LITRE)));

        System.out.println(new Quantity<>(3.78541, VolumeUnit.LITRE).equals(new Quantity<>(1.0, VolumeUnit.GALLON)));


        System.out.println("\n----- Unit Conversions -----");

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE));

        System.out.println(new Quantity<>(2.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE));

        System.out.println(new Quantity<>(500.0, VolumeUnit.MILLILITRE).convertTo(VolumeUnit.GALLON));

        System.out.println(new Quantity<>(0.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE));

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.LITRE));


        System.out.println("\n----- Addition (Implicit Target Unit) -----");

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(2.0, VolumeUnit.LITRE)));

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));

        System.out.println(new Quantity<>(500.0, VolumeUnit.MILLILITRE).add(new Quantity<>(0.5, VolumeUnit.LITRE)));

        System.out.println(new Quantity<>(2.0, VolumeUnit.GALLON).add(new Quantity<>(3.78541, VolumeUnit.LITRE)));


        System.out.println("\n----- Addition (Explicit Target Unit) -----");

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE));

        System.out.println(new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(3.78541, VolumeUnit.LITRE), VolumeUnit.GALLON));

        System.out.println(new Quantity<>(500.0, VolumeUnit.MILLILITRE).add(new Quantity<>(1.0, VolumeUnit.LITRE), VolumeUnit.GALLON));

        System.out.println(new Quantity<>(2.0, VolumeUnit.LITRE).add(new Quantity<>(4.0, VolumeUnit.GALLON), VolumeUnit.LITRE));


        System.out.println("\n----- Category Incompatibility -----");

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, LengthUnit.FEET)));

        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
    }
}
