package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-5;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(2.0, Length.LengthUnit.FEET);

        Length result = a.add(b);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {

        Length a = new Length(6.0, Length.LengthUnit.INCHES);
        Length b = new Length(6.0, Length.LengthUnit.INCHES);

        Length result = a.add(b);

        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = feet.add(inches);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {

        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        Length feet = new Length(1.0, Length.LengthUnit.FEET);

        Length result = inches.add(feet);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {

        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);

        Length result = yard.add(feet);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {

        Length cm = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, Length.LengthUnit.INCHES);

        Length result = cm.add(inch);

        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    void testAddition_Commutativity() {

        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length result1 = a.add(b);
        Length result2 = b.add(a);

        assertEquals(result1.convertTo(Length.LengthUnit.INCHES), result2.convertTo(Length.LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testAddition_WithZero() {

        Length a = new Length(5.0, Length.LengthUnit.FEET);
        Length zero = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = a.add(zero);

        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_NegativeValues() {

        Length a = new Length(5.0, Length.LengthUnit.FEET);
        Length b = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = a.add(b);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_NullSecondOperand() {

        Length a = new Length(1.0, Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> a.add(null));
    }

    @Test
    void testAddition_LargeValues() {

        Length a = new Length(1e6, Length.LengthUnit.FEET);
        Length b = new Length(1e6, Length.LengthUnit.FEET);

        Length result = a.add(b);

        assertEquals(2e6, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_SmallValues() {

        Length a = new Length(0.001, Length.LengthUnit.FEET);
        Length b = new Length(0.002, Length.LengthUnit.FEET);

        Length result = a.add(b);

        assertEquals(0.003, result.getValue(), EPSILON);
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }
}