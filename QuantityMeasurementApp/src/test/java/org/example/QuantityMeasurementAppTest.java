package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-5;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result = a.add(b, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result = a.add(b, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result = a.add(b, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), 1e-3);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        Length a = new Length(1.0, LengthUnit.INCHES);
        Length b = new Length(1.0, LengthUnit.INCHES);

        Length result = a.add(b, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length a = new Length(2.0, LengthUnit.YARDS);
        Length b = new Length(3.0, LengthUnit.FEET);

        Length result = a.add(b, LengthUnit.YARDS);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length a = new Length(2.0, LengthUnit.YARDS);
        Length b = new Length(3.0, LengthUnit.FEET);

        Length result = a.add(b, LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result1 = a.add(b, LengthUnit.YARDS);
        Length result2 = b.add(a, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        Length a = new Length(5.0, LengthUnit.FEET);
        Length zero = new Length(0.0, LengthUnit.INCHES);

        Length result = a.add(zero, LengthUnit.YARDS);

        assertEquals(1.667, result.getValue(), 1e-3);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length a = new Length(5.0, LengthUnit.FEET);
        Length b = new Length(-2.0, LengthUnit.FEET);

        Length result = a.add(b, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class,
                () -> a.add(b, null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length a = new Length(1000.0, LengthUnit.FEET);
        Length b = new Length(500.0, LengthUnit.FEET);

        Length result = a.add(b, LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length a = new Length(12.0, LengthUnit.INCHES);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result = a.add(b, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {

        LengthUnit[] units = LengthUnit.values();

        for (LengthUnit u1 : units) {
            for (LengthUnit u2 : units) {
                for (LengthUnit target : units) {

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

        Length a = new Length(2.54, LengthUnit.CENTIMETERS);
        Length b = new Length(1.0, LengthUnit.INCHES);

        Length result = a.add(b, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
    }
}