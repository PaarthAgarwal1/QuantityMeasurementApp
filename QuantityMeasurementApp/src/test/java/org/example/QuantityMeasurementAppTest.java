package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityMeasurementApp.convert(24.0, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityMeasurementApp.convert(1.0, Length.LengthUnit.YARDS, Length.LengthUnit.INCHES);
        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = QuantityMeasurementApp.convert(72.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.convert(2.54, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = QuantityMeasurementApp.convert(6.0, Length.LengthUnit.FEET, Length.LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double value = 5.0;
        double converted = QuantityMeasurementApp.convert(value, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        double roundTrip = QuantityMeasurementApp.convert(converted, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
        assertEquals(value, roundTrip, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.convert(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityMeasurementApp.convert(-1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(-12.0, result, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.convert(1.0, null, Length.LengthUnit.INCHES));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.convert(Double.NaN, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));
    }

    @Test
    void testConversion_PrecisionTolerance() {
        double result = QuantityMeasurementApp.convert(1.0, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES);
        assertEquals(0.393701, result, EPSILON);
    }
}