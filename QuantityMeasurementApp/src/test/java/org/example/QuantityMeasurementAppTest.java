package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // 1
    @Test
    public void testEquality_YardToYard_SameValue() {
        Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length yard2 = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(yard1.equals(yard2));
    }

    // 2
    @Test
    public void testEquality_YardToYard_DifferentValue() {
        Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length yard2 = new Length(2.0, Length.LengthUnit.YARDS);
        assertFalse(yard1.equals(yard2));
    }

    // 3
    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        assertTrue(yard.equals(feet));
    }

    // 4
    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(feet.equals(yard));
    }

    // 5
    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(yard.equals(inches));
    }

    // 6
    @Test
    public void testEquality_InchesToYard_EquivalentValue() {
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(inches.equals(yard));
    }

    // 7
    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(yard.equals(feet));
    }

    // 8
    @Test
    public void testEquality_centimetersToInches_EquivalentValue() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length inches = new Length(0.393701, Length.LengthUnit.INCHES);
        assertTrue(cm.equals(inches));
    }

    // 9
    @Test
    public void testEquality_centimetersToFeet_NonEquivalentValue() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(cm.equals(feet));
    }

    // 10
    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    // 11
    @Test
    public void testEquality_YardWithNullUnit() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }

    // 12
    @Test
    public void testEquality_YardSameReference() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(yard.equals(yard));
    }

    // 13
    @Test
    public void testEquality_YardNullComparison() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }

    // 14
    @Test
    public void testEquality_CentimetersWithNullUnit() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertFalse(cm.equals(null));
    }

    // 15
    @Test
    public void testEquality_CentimetersSameReference() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertTrue(cm.equals(cm));
    }

    // 16
    @Test
    public void testEquality_CentimetersNullComparison() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertFalse(cm.equals(null));
    }

    // 17
    @Test
    public void testEquality_AllUnits_ComplexScenario() {

        Length yards = new Length(2.0, Length.LengthUnit.YARDS);
        Length feet = new Length(6.0, Length.LengthUnit.FEET);
        Length inches = new Length(72.0, Length.LengthUnit.INCHES);

        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches));
    }
}