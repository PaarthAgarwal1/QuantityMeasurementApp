package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-5;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = a.add(b, Length.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = a.add(b, Length.LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = a.add(b, Length.LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), 1e-3);
        assertEquals(Length.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        Length a = new Length(1.0, Length.LengthUnit.INCHES);
        Length b = new Length(1.0, Length.LengthUnit.INCHES);

        Length result = a.add(b, Length.LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length a = new Length(2.0, Length.LengthUnit.YARDS);
        Length b = new Length(3.0, Length.LengthUnit.FEET);

        Length result = a.add(b, Length.LengthUnit.YARDS);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length a = new Length(2.0, Length.LengthUnit.YARDS);
        Length b = new Length(3.0, Length.LengthUnit.FEET);

        Length result = a.add(b, Length.LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length result1 = a.add(b, Length.LengthUnit.YARDS);
        Length result2 = b.add(a, Length.LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        Length a = new Length(5.0, Length.LengthUnit.FEET);
        Length zero = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = a.add(zero, Length.LengthUnit.YARDS);

        assertEquals(1.667, result.getValue(), 1e-3);
        assertEquals(Length.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length a = new Length(5.0, Length.LengthUnit.FEET);
        Length b = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = a.add(b, Length.LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class,
                () -> a.add(b, null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length a = new Length(1000.0, Length.LengthUnit.FEET);
        Length b = new Length(500.0, Length.LengthUnit.FEET);

        Length result = a.add(b, Length.LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length a = new Length(12.0, Length.LengthUnit.INCHES);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = a.add(b, Length.LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {

        Length.LengthUnit[] units = Length.LengthUnit.values();

        for (Length.LengthUnit u1 : units) {
            for (Length.LengthUnit u2 : units) {
                for (Length.LengthUnit target : units) {

                    Length a = new Length(1.0, u1);
                    Length b = new Length(1.0, u2);

                    Length result = a.add(b, target);

                    assertNotNull(result);
                    assertEquals(target, result.getUnit());
                }
            }
        }
    }

    @Test
    void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

        Length a = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length b = new Length(1.0, Length.LengthUnit.INCHES);

        Length result = a.add(b, Length.LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
    }
}